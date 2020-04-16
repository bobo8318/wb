package pub.imba.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import pub.imba.util.TextUtil;

import java.util.Collection;


public class UserRealm extends AuthorizingRealm {

    ///授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return null;
    }

    /**
     * 验证当前登录的Subject
     * Controller 中 login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 获取登录用户名
        String phoneno = (String) authcToken.getPrincipal();
        // 获取密码
        String code = new String((char[]) authcToken.getCredentials());

        //SecurityUtils.getSubject().getSession();

         String storecode = SecurityUtils.getSubject().getSession().getAttribute("coder").toString();
         //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
         SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                 phoneno,
                 storecode,//数据库中存储的真实密码
                 //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                 getName()//当前realm名字
         );
         return authenticationInfo;

    }
}
