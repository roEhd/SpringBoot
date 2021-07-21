package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    //다른 곳에서 쓸 것이 아니기 때문에 굳이 public으로 할 필요 없다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //save 테스트 하기
        Member member = new Member();
        member.setName("hello");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Optional을 반환받을 때 get()으로 받을 수 있다.

        assertThat(member).isEqualTo(result); //static method임
    }
    @Test
    public void findByName(){
        //findByName 테스트하기
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("boot");
        repository.save(member2);

        Member result = repository.findByName("spring").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
