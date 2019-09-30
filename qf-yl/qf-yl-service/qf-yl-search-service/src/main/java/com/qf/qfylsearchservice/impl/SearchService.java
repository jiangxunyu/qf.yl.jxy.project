package com.qf.qfylsearchservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.mapper.ProductMapper;
import com.qf.yl.searh.api.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Service
public class SearchService implements ISearchService {

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private SolrClient solrClient;

    public void addProductSolr(Product product){

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", product.getId());
        document.addField("product_name", product.getName());
        document.addField("product_price", product.getPrice().intValue());
        document.addField("product_image", product.getImage());
        document.addField("product_sale_point", product.getSalePoint());
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultBean initSolrData() {

        List<Product> products = mapper.selectProducts();
        if(products!=null) {
            for (Product product : products) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",product.getId());
                document.addField("product_name",product.getName());
                document.addField("product_price",product.getPrice().intValue());
                document.addField("product_image",product.getImage());
                document.addField("product_sale_point",product.getSalePoint());
                try {
                    solrClient.add(document);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResultBean.error("初始化数据失败");
                }
            }
            try {
                solrClient.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResultBean.success("初始化数据成功");
        }
        return ResultBean.error("初始化数据失败");
    }

    /**
     * 带高亮的显示查询商品
     */
    @Override
    public ResultBean<List<Product>> searchByKeyword(String keyword) {

        SolrQuery query = new SolrQuery();
        query.setStart(0);
        query.setRows(10);
        //设置复制域
        query.set("df","product_keyword");
        query.setQuery(keyword);

        //设置高亮
        query.setHighlight(true);
        query.setHighlightSimplePre("<span style='color:red;display: inline-block' >");
        query.setHighlightSimplePost("<span/>");
        query.addHighlightField("product_name");

        List<Product> products = null;

        try {
            QueryResponse response = solrClient.query(query);
            //获取document数据
            SolrDocumentList documents = response.getResults();
            //获取高亮数据
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            products = new ArrayList<>();

            for (SolrDocument document : documents) {
                Product product = new Product();
                product.setId(Long.parseLong((String)document.getFieldValue("id")));
                product.setName((String)document.getFieldValue("product_name"));
                product.setImage((String)document.getFieldValue("product_image"));
                product.setPrice(new BigDecimal((Float)document.getFieldValue("product_price")));
                product.setSalePoint((String)document.getFieldValue("product_sale_point"));

                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id"));
                List<String> nameList = map.get("product_name");
                if(nameList!=null&&nameList.size()>0){
                    String name = nameList.get(0);
                    product.setName(name);
                }
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("服务器繁忙");
        }

        ResultBean<List<Product>> resultBean = new ResultBean<>();
        resultBean.setErrno(0);
        resultBean.setData(products);
        resultBean.setMessage("查询成功");
        return resultBean;
    }

}
