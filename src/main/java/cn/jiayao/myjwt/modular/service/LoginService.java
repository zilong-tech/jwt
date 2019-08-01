package cn.jiayao.myjwt.modular.service;

import cn.jiayao.myjwt.modular.entity.Customer;
import cn.jiayao.myjwt.modular.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类 名: LoginService
 * 描 述:
 * 作 者: 黄加耀
 * 创 建: 2019/4/30 : 10:52
 * 邮 箱: huangjy19940202@gmail.com
 *
 * @author: jiaYao
 */
@Service
public class LoginService {

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 登录
     *
     * @param customerId
     * @return
     */
    public String login(String customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return tokenUtils.createDefaultTokenStringSm4(customer);
    }

    /**
     * 根据id查用户
     *
     * @param customerId
     * @return
     */
    public Customer findCustomerById(String customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }



}
