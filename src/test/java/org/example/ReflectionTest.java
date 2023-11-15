package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Controller 애노테이션이 설정되어 있는 모든 클래스를 찾아서 출력
 * [ReFlection]s
 * - 힙 영역에 로드되어 있는 클래스 타입의 객체를 통해 필드/메소드/생성자를 접근 제어자와 상관 없이 사용할 수 있도록 지원하는 API
 * - 컴파일 시점이 아닌 런타임 시점에 동적으로 특정 클래스의 정보를 추출할 수 있는 프로그래밍 기법
 * - 주로 프레임 워크 또는 라이브러리 개발 시 사용
 */
public class ReflectionTest {
    private  static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        //org.example의 패키지의 class에서 Controller 애노테이션을 찾아 담는 처리
        Set<Class<?>> beans = getTypesAnnotateWith(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared Constructors [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared Methods [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException {
        Class<User> clazz = User.class;
        User user = new User("serverwizard", "홍종완");
        Class<? extends  User> clazz2 = user.getClass();

        Class<?> clazz3 = Class.forName("org.example.model.User");
        logger.debug("clazz: [{}]", clazz);
        logger.debug("clazz: [{}]", clazz2);
        logger.debug("clazz: [{}]", clazz3);
        //Tdd2
        //assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        //assertThat(clazz3 == clazz).isTrue();
    }

    private static Set<Class<?>> getTypesAnnotateWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
//        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
//        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
        return beans;
    }
}
