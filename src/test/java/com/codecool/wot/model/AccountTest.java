package com.codecool.wot.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    public static Stream<Account> userProvider() {
        Mentor mentor = new Mentor("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3", "10");
        Student student = new Student("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3", "10");
        Admin admin = new Admin("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3");
        return Stream.of(mentor, student, admin);
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsAccountNotNull(Account account) {
        assertNotNull(account);
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsGetIDWorking(Account account) {
        assertEquals("3", account.getId());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsGetNameWorking(Account account) {
        assertEquals("Jakub", account.getName());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsSetNameWorking(Account account) {
        account.setName("Kuba");
        assertEquals("Kuba", account.getName());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsGetSurnameWorking(Account account) {
        assertEquals("Janiszewski", account.getSurname());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsSetSurnameWorking(Account account) {
        account.setSurname("Kos");
        assertEquals("Kos", account.getSurname());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsGetEmailWorking(Account account) {
        assertEquals("jj@cc.pl", account.getEmail());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsSetEmailWorking(Account account) {
        account.setEmail("kk@cc.pl");
        assertEquals("kk@cc.pl", account.getEmail());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsGetLoginWorking(Account account) {
        assertEquals("Jaja", account.getLogin());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsSetLoginWorking(Account account) {
        account.setLogin("Kuba");
        assertEquals("Kuba", account.getLogin());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsGetPasswordWorking(Account account) {
        assertEquals("jajaja", account.getPassword());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void testIsSetPasswordWorking(Account account) {
        account.setPassword("janiszewski");
        assertEquals("janiszewski", account.getPassword());
    }

    @Test
    void testIsMentorGetSchoolClassWorking() {
        Mentor mentor = new Mentor("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3", "10");
        assertNull(mentor.getSchoolClass());
    }

    @Test
    void testIsStudentGetSchoolClassWorking() {
        Student student = new Student("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3", "10");
        assertNull(student.getSchoolClass());
    }

    @Test
    void testIsMentorToStringWorking() {
        Mentor mentor = new Mentor("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3", "10");
        assertEquals("ID: 3, name: Jakub, surname: Janiszewski, email: jj@cc.pl, login: Jaja",
                mentor.toString());
    }

    @Test
    void testIsStudentToStringWorking() {
        Student student = new Student("Jakub", "Janiszewski", "jj@cc.pl",
                "Jaja", "jajaja", "3", "10");
        assertEquals("ID: 3, name: Jakub, surname: Janiszewski, email: jj@cc.pl, login: Jaja",
                student.toString());
    }
}