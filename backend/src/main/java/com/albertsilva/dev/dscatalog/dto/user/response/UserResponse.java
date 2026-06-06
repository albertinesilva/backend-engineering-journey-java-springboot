package com.albertsilva.dev.dscatalog.dto.user.response;

import java.util.Set;

import com.albertsilva.dev.dscatalog.dto.role.response.RoleResponse;

/**
 * DTO de resposta para operações relacionadas a usuários.
 *
 * <p>
 * Esta classe é utilizada para transferir dados de usuários
 * do backend para o frontend, encapsulando as informações
 * relevantes do usuário, como ID, nome, email e roles.
 *
 * <p>
 * O DTO é imutável e utiliza um record para simplificar a
 * definição da classe e garantir a integridade dos dados.
 *
 * <p>
 * Exemplo de uso:
 *
 * <pre>{@code
 * UserResponse user = new UserResponse(
 *     1L,
 *     "John",
 *     "Doe",
 *     "john.doe@example.com",
 *     Set.of(new RoleResponse(1L, "ROLE_USER")));
 * }</pre>
 *
 * @param id        Identificador único do usuário
 * @param firstName Primeiro nome do usuário
 * @param lastName  Sobrenome do usuário
 * @param email     Endereço de email do usuário
 * @param roles     Conjunto de roles associadas ao usuário
 */
public record UserResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    Set<RoleResponse> roles) {

}
