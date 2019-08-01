package cn.jiayao.myjwt.modular.utils;

import cn.jiayao.myjwt.jwts.data.Header;
import cn.jiayao.myjwt.jwts.data.JwtClaims;
import cn.jiayao.myjwt.jwts.data.Jwts;
import cn.jiayao.myjwt.jwts.date.FailureTime;
import cn.jiayao.myjwt.jwts.date.FailureTimeUtils;
import cn.jiayao.myjwt.modular.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类 名: TokenUtils
 * 描 述:
 * 作 者: 黄加耀
 * 创 建: 2019/8/1 : 18:25
 * 邮 箱: huangjy19940202@gmail.com
 *
 * @author: jiaYao
 */
@Component
public class TokenUtils {


    @Value("${jwt.safety.secret}")
    private String jwtSafetySecret;

    @Value("${jwt.valid.time}")
    private int jwtValidTime;

    /**
     * 使用SM4加密生成token,指定加密言值
     *
     * @param customer
     * @return
     */
    public String createTokenStringSm4(Customer customer) {
        if (customer == null) throw new RuntimeException("入参不能为null");
        String jwtToken = null;
        try {
            jwtToken = commonJwtToken(Header.SM4, jwtSafetySecret, customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken.replaceAll("\r\n","");
    }

    /**
     * 使用AES生成token,指定加密言值
     *
     * @param customer
     * @return
     */
    public String createTokenStringAes(Customer customer) {
        if (customer == null) throw new RuntimeException("入参不能为null");
        String jwtToken = null;
        try {
            jwtToken = commonJwtToken(Header.AES, jwtSafetySecret, customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken.replaceAll("\r\n","");
    }

    /**
     * 使用AES生成token,指定加密言值
     *
     * @param customer
     * @return
     */
    public String createTokenStringAesSm3(Customer customer) {
        if (customer == null) throw new RuntimeException("入参不能为null");
        String jwtToken = null;
        try {
            jwtToken = commonJwtToken(Header.SM3, jwtSafetySecret, customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken.replaceAll("\r\n","");
    }

    /**
     * 使用Sm4生成token，使用系统默认言值
     *
     * @param customer
     * @return
     */
    public String createDefaultTokenStringSm4(Customer customer) {
        if (customer == null) throw new RuntimeException("入参不能为null");
        String jwtToken = null;
        try {
            jwtToken = commonJwtToken(Header.SM4, null, customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken.replaceAll("\r\n","");
    }

    /**
     * 使用Aes生成token，使用系统默认言值
     *
     * @param customer
     * @return
     */
    public String createDefaultTokenStringAes(Customer customer) {
        if (customer == null) throw new RuntimeException("入参不能为null");
        String jwtToken = null;
        try {
            jwtToken = commonJwtToken(Header.AES, null, customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken.replaceAll("\r\n","");
    }

    /**
     * 使用Sm3生成token，使用系统默认言值
     *
     * @param customer
     * @return
     */
    public String createDefaultTokenStringSm3(Customer customer) {
        if (customer == null) throw new RuntimeException("入参不能为null");
        String jwtToken = null;
        try {
            jwtToken = commonJwtToken(Header.SM3, null, customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken.replaceAll("\r\n","");
    }

    /**
     * 公共代码提取
     * @param header
     * @param jwtSafetySecret 可为null， 会使用默认值
     * @param customer
     * @return
     * @throws Exception
     */
    private String commonJwtToken(Header header, String jwtSafetySecret, Customer customer) throws Exception {
       return Jwts.header(header, jwtSafetySecret)
                .payload(new JwtClaims()
                        .put("id", customer.getId())
                        .put("name", customer.getName())
                        .put("phone", customer.getPhone())
                        .put("failureTime", FailureTimeUtils.creatValidTime(FailureTime.DAY, jwtValidTime))
                        .put("mytest", "我的个性属性"))
                .compact();
    }


}
