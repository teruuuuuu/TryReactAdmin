package jp.co.teruuu.ec_modeling.init_data;

import jp.co.teruuu.ec_modeling.app.domain.user.UserEntity;
import jp.co.teruuu.ec_modeling.app.domain.user.model.User;
import jp.co.teruuu.ec_modeling.app.domain.user.model.UserInfo;
import jp.co.teruuu.ec_modeling.app.domain.user.repository.UserRepository;
import jp.co.teruuu.ec_modeling.infla.encoder.PassEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInitService {
  @Autowired
  UserRepository userRepository;
  static PasswordEncoder enc = PassEncoder.encoder();

  public void insert(){
    for(UserEntity ue: userDatas()){
      userRepository.save(ue);
    }
  }

  List<UserEntity> userDatas(){
    List<UserEntity> datas = new ArrayList<UserEntity>();
    datas.add(makeUser("user1","user1","user1_address", "user1_postalcode"));
    datas.add(makeUser("user2","user2","user2_address", "user2_postalcode"));
    datas.add(makeUser("user3","user3","user3_address", "user3_postalcode"));
    datas.add(makeUser("user4","user4","user4_address", "user4_postalcode"));
    datas.add(makeUser("user5","user5","user5_address", "user5_postalcode"));
    datas.add(makeUser("user6","user6","user6_address", "user6_postalcode"));
    datas.add(makeUser("user7","user7","user7_address", "user7_postalcode"));
    datas.add(makeUser("user8","user8","user8_address", "user8_postalcode"));
    datas.add(makeUser("user9","user9","user9_address", "user9_postalcode"));
    datas.add(makeUser("user10","user10","user10_address", "user10_postalcode"));
    return datas;
  }

  UserEntity makeUser(String name, String password, String address, String postalCode){
    User user = new User(name, enc.encode(password));
    UserInfo userInfo = new UserInfo(address, postalCode);
    user.setUserInfo(userInfo);
    return new UserEntity(user);
  }
}
