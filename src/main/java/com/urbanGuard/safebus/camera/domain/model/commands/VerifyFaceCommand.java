package com.urbanGuard.safebus.camera.domain.model.commands;

/**
 * employeeId  -> a quién se le está verificando el rostro (referencia a IAM.Employee)
 * capturedImageRef -> referencia/URL/base64 corto de la captura hecha por la cámara del bus
 */
public record VerifyFaceCommand(Long employeeId, String capturedImageRef) {}
