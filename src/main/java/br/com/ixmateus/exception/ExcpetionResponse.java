package br.com.ixmateus.exception;

import java.util.Date;

public record ExcpetionResponse(Date timestamp, String message, String details) {}