package com.qaproject.Cookbook.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee does not exist with that ID")
public class ChefNotFound extends EntityNotFoundException {

}