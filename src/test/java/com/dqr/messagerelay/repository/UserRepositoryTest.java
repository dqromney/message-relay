package com.dqr.messagerelay.repository;

import com.dqr.messagerelay.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
// Run tests against a real database
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private User goodUser, goodUser2, badEmailUser, badFirstNameUser, badUsernameUser;

    @Before
    public void init() {
        goodUser = new User("First", "Last", "test@test.com", "tester", true);
        goodUser2 = new User("First2", "Last2", "test2@test.com", "tester2", true);
        badEmailUser = new User("First", "Last", "test%test.com", "tester", true);
        badFirstNameUser = new User(null, "Last", "test@test.com", "tester", true);
        badUsernameUser = new User("First", "Last", "test@test.com", "", true);
    }

    @Test
    public void test_create_user_success() throws Exception {
        repository.save(goodUser);
        User user = this.repository.findByEmail("test@test.com");
        assertThat(user.getFirstName()).isEqualTo("First");
        assertThat(user.getLastName()).isEqualTo("Last");
        assertThat(user.getEmail()).isEqualTo("test@test.com");
        assertThat(user.getUsername()).isEqualTo("tester");
        assertThat(user.isActive()).isEqualTo(true);
    }

    @Test
    public void test_create_user_with_bad_email() throws Exception {
        try {
            User user = repository.save(badEmailUser);
            assertThat(user == null);
        } catch(javax.validation.ConstraintViolationException e) {
            assert(e.getMessage().contains("not a well-formed email address"));
        }
    }

    @Test
    public void test_create_user_with_first_name_null_failure() throws Exception {
        User user = null;
        try {
            user = repository.save(badFirstNameUser);
            assertThat(user == null);
        } catch(javax.validation.ConstraintViolationException e) {
            assert(e.getMessage().contains("'may not be empty"));
        }
    }

    @Test
    public void test_create_user_with_username_failure() throws Exception {
        User user = null;
        try {
            user = repository.save(badUsernameUser);
            assertThat(user == null);
        } catch(javax.validation.ConstraintViolationException e) {
            assert(e.getMessage().contains("'may not be empty"));
        }
    }

    @Test
    public void test_update_user_success() throws Exception {
        repository.save(goodUser);
        User user = repository.findByEmail("test@test.com");
        user.setFirstName("Updated First");
        User updatedUser = repository.save(user);
        // Check updated field
        assertThat(updatedUser.getFirstName()).isEqualTo("Updated First");
        // Check unaffected fields
        assertThat(updatedUser.getLastName()).isEqualTo("Last");
        assertThat(updatedUser.getEmail()).isEqualTo("test@test.com");
        assertThat(updatedUser.getUsername()).isEqualTo("tester");
        assertThat(updatedUser.isActive()).isEqualTo(true);
    }

    @Test
    public void test_user_exists_success() throws Exception {
        User user = repository.save(goodUser);
        Boolean exists = repository.exists(user.getId());
        assertThat(exists);
    }

    @Test
    public void test_find_one_user_success() throws Exception {
        User user = repository.save(goodUser);
        User findUser = repository.findOne(user.getId());
        assertThat(findUser != null);
    }

    @Test
    public void test_find_all_users_success() throws Exception {
        repository.save(goodUser);
        repository.save(goodUser2);
        Iterable<User> userIter = repository.findAll();
        userIter.forEach(user -> assertThat(user.getId() > 0));
    }

    @Test
    public void test_delete_users_success() throws Exception {
        // Create two records
        repository.save(goodUser);
        repository.save(goodUser2);
        // Get them back
        Iterable<User> userIter = repository.findAll();
        // Test that each is found
        userIter.forEach(user -> assertThat(user.getId() > 0));
        // Delete records
        repository.delete(userIter);
        // Try to find them
        userIter = repository.findAll();
        // Test that none were found
        userIter.forEach(user -> assertThat(user == null));
    }


}
