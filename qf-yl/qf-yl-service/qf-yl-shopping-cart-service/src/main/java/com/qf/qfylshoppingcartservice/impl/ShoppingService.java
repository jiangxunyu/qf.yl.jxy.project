package com.qf.qfylshoppingcartservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.constant.RedisConstant;
import com.qf.dto.ProductCart;
import com.qf.dto.ResultBean;
import com.qf.entity.Product;
import com.qf.entity.ShoppingCart;
import com.qf.mapper.ProductMapper;
import com.qf.mapper.ShoppingCartMapper;
import com.qf.shopping.cart.api.IShoppingCartService;
import com.qf.util.GeneratorRedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Service
public class ShoppingService implements IShoppingCartService {

    @Autowired
    private ShoppingCartMapper mapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ResultBean addProduct(Product product) {

        ShoppingCart shoppingCart1 = mapper.selectByProductId(product.getId());
        if (shoppingCart1 == null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setProductId(product.getId());
            shoppingCart.setProductName(product.getName());
            shoppingCart.setProductImage(product.getImage());
            shoppingCart.setProductMount(1);
            shoppingCart.setProductPrice(product.getPrice());

            int i = mapper.insert(shoppingCart);
            if (i >= 1) {
                return ResultBean.success("商品添加成功");
            }
            return ResultBean.error("商品添加失败");
        } else {
            if (shoppingCart1.getProductMount() >= 1) {
                shoppingCart1.setProductMount(shoppingCart1.getProductMount() + 1);
                int i = mapper.updateByPrimaryKey(shoppingCart1);
                return ResultBean.success("添加商品成功");
            }
        }

        return ResultBean.error("商品添加失败");
    }

    @Override
    public List<ShoppingCart> selectShoppingCart() {

        List<ShoppingCart> shoppingCarts = mapper.selectShoppingCart();
        return shoppingCarts;
    }

    @Override
    public void deleteById(Long id) {

        mapper.deleteByPrimaryKey(id);

    }

    /**
     * 测试未登录状态下获取购物车接口
     *
     * @param uuid
     * @return
     */
    @Override
    public ResultBean showVisitorCart(String uuid) {

        //组织键
        String key = GeneratorRedisKey.getKey(RedisConstant.USER_CART, uuid);

        //去redis中获取商品列表
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Object o = redisTemplate.opsForValue().get(key);

        if (o == null) {
            return ResultBean.error("当前用户没有购物车");
        }

        //商品列表不为空
        List<ShoppingCart> cartList = (List<ShoppingCart>) o;

        //获取redis中购物车商品信息
        List<ShoppingCart> shoppingCartList = (List<ShoppingCart>) redisTemplate.opsForValue().get(key);
        //刷新商品保存时间
        redisTemplate.expire(key, 30, TimeUnit.DAYS);

        return ResultBean.success(shoppingCartList, "获取购物车信息成功");
    }

    /**
     * 测试未登录状态下添加商品到购物车接口
     *
     * @param id
     * @param count
     * @param uuid
     * @return
     */
    @Override
    public ResultBean addToCart(Long id, int count, String uuid) {

        /*
        往redis中添加商品
         */

        //根据id查询商品信息
        Product product = productMapper.selectByPrimaryKey(id);

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        String key = GeneratorRedisKey.getKey(RedisConstant.USER_CART, uuid);

        Object o = redisTemplate.opsForValue().get(key);

        if (o == null) {
            //1.redis中没有该用户对应的集合
            //创建商品集合
            List<ProductCart> cartList = new ArrayList<>();
            //封装购物车商品信息
            ShoppingCart cart = new ShoppingCart();
            cart.setProductId(product.getId());
            cart.setProductName(product.getName());
            cart.setProductPrice(product.getPrice());
            cart.setProductImage(product.getImage());
            cart.setProductMount(count);
            cart.setTotalPrice(new BigDecimal(count * product.getPrice().intValue()));

            ProductCart productCart = new ProductCart();
            productCart.setShoppingCart(cart);
            productCart.setDate(new Date());

            //商品集合（购物车）中添加商品
            cartList.add(productCart);

            //存入redis
            opsRedisCart(key, cartList);
            return ResultBean.success(cartList, "添加购物车成功");

        }

        List<ProductCart> cartList = (List<ProductCart>) o;
        for (ProductCart cart : cartList) {

            if (cart.getShoppingCart().getProductId().longValue() == id) {

                //有商品集合且有该商品
                cart.getShoppingCart().setProductMount(cart.getShoppingCart().getProductMount() + count);
                cart.setDate(new Date());
                opsRedisCart(key, cartList);

                return ResultBean.success(cartList, "添加购物车成功");
            }
        }

        //有商品集合但没有该商品
        ProductCart cart = new ProductCart();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProductId(product.getId());
        shoppingCart.setProductName(product.getName());
        shoppingCart.setProductPrice(product.getPrice());
        shoppingCart.setProductImage(product.getImage());
        shoppingCart.setProductMount(count);
        shoppingCart.setTotalPrice(new BigDecimal(count * product.getPrice().intValue()));

        cart.setDate(new Date());
        cart.setShoppingCart(shoppingCart);
        cartList.add(cart);
        opsRedisCart(key, cartList);
        return ResultBean.success(cartList, "添加购物车成功");

    }

    /**
     * 更新购物车
     *
     * @param productId
     * @param count
     * @param uuid
     * @return
     */
    @Override
    public ResultBean updataCart(Long productId, int count, String uuid) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //组织键
        String key = GeneratorRedisKey.getKey(RedisConstant.USER_CART, uuid);

        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            ResultBean.error("当前用户没有购物车");
        }

        //购物车不为空
        List<ProductCart> cartList = (List<ProductCart>) o;
        for (ProductCart cart : cartList) {

            if (cart.getShoppingCart().getProductId().longValue() == productId) {

                //更新商品数量
                cart.getShoppingCart().setProductMount(count);
                cart.setDate(new Date());
                //更新redis
                opsRedisCart(key, cartList);

                return ResultBean.success(cart, "更新商品成功");
            }
        }
        return ResultBean.error("更新商品失败");
    }

    /**
     * 未登录状态下删除商品
     *
     * @param productId
     * @param uuid
     * @return
     */
    @Override
    public ResultBean deleteProduct(Long productId, String uuid) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //组织键
        String key = GeneratorRedisKey.getKey(RedisConstant.USER_CART, uuid);

        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            return ResultBean.error("当前用户没有购物车");
        }

        List<ProductCart> cartList = (List<ProductCart>) o;
//        Iterator<ShoppingCart> iterator = shoppingCartList.iterator();
        Iterator<ProductCart> iterator = cartList.iterator();
        while (iterator.hasNext()) {

            //如果有下一个
            ProductCart productCart = iterator.next();
            if (productCart.getShoppingCart().getProductId().longValue() == productId) {

                iterator.remove();
                //更新redis
              opsRedisCart(key,cartList);
                return ResultBean.success(cartList, "删除成功");
            }
        }
        return ResultBean.error("删除失败");
    }

    /**
     * 将集合存入到redis中并且超时时间是30天
     *
     * @param key
     * @param list
     */
    public void opsRedisCart(String key, List<ProductCart> list) {
        //list是一个后添加排在后面的状态，调整顺序：

        Collections.sort(list, new Comparator<ProductCart>() {
            @Override
            public int compare(ProductCart o1, ProductCart o2) {
                /*
                负数 就表示前者比后者小
                0    就表示两者相等
                整数 就表示前者比后者大
                 */
                return (int) (o2.getDate().getTime() - o1.getDate().getTime());
            }
        });

        //将集合存入到redis中
        redisTemplate.opsForValue().set(key, list);
        //设置超时时间
        redisTemplate.expire(key, 30, TimeUnit.DAYS);

    }
}
