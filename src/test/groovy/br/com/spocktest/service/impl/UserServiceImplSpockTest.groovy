package br.com.spocktest.service.impl


import br.com.spocktest.exception.NotFoundException
import br.com.spocktest.model.User
import br.com.spocktest.model.enums.JobTitle
import br.com.spocktest.repository.UserRepository
import spock.lang.Specification

class UserServiceImplSpockTest extends Specification {

    def repository = Mock(UserRepository);

    def service = new UserServiceImpl(repository);

    def "should find user by id"() {
        given:
        def id = 3L
        def user = User.builder().id(id).build()

        when:
        def result = service.findById(id)

        then:
        1 * repository.findById(id) >> Optional.of(user)
        0 * _
        result == user
    }

    def "should find by id and throw NotFoundException"() {
        given:
        def id = 3L

        when:
        service.findById(id)

        then:
        1 * repository.findById(id) >> Optional.empty()
        0 * _
        def e = thrown(NotFoundException)
        e.getMessage() == String.format("User with id %s was not found", id)
    }

    def "should find all users"() {
        given:
        def users = [User.builder().id(1L).build(), User.builder().id(1L).build()]

        when:
        def result = service.findAll()

        then:
        1 * repository.findAll() >> users
        0 * _
        result == users
    }

    def "should get user salary"() {
        given:
        def id = 3L
        def manager = User.builder().jobTitle(jobTitle).build()

        when:
        def result = service.getUserSalary(id)

        then:
        1 * repository.findById(id) >> Optional.of(manager)
        0 * _
        result == salary

        where:
        jobTitle             | salary
        JobTitle.MANAGER | "R\$ 50.000,00"
        JobTitle.COORDINATOR | "R\$ 30.000,00"
        JobTitle.SPECIALIST  | "R\$ 20.000,00"
        JobTitle.ENGINEER    | "R\$ 1.000,00"
    }

}
