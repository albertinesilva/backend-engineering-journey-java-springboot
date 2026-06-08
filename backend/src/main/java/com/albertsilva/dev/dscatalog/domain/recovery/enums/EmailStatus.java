package com.albertsilva.dev.dscatalog.domain.recovery.enums;

public enum EmailStatus {

  /**
   * Email criado e aguardando envio.
   */
  PENDING,

  /**
   * Email enviado com sucesso.
   */
  SENT,

  /**
   * Falha durante o envio.
   */
  ERROR
}
