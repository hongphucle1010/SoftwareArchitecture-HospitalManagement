package com.hcmutnightowls.authenticationservice.service.Interface.Strategy;

public interface UserFinderStrategy<T> {
    T findBySubject(String subject);
}
