package com.albertsilva.dev.dscatalog.dto.category.response;

/**
 * DTO utilizado para retorno de dados detalhados de categoria na API.
 *
 * <p>
 * Contém as mesmas informações que {@link CategoryResponse}, mas pode ser
 * estendido no futuro para incluir detalhes adicionais sem impactar a resposta
 * básica.
 * </p>
 *
 * @param id          identificador da categoria
 * @param name        nome da categoria
 * @param description descrição da categoria
 * @param active      indica se a categoria está ativa
 */
public record CategoryDetailsResponse(Long id, String name, String description, boolean active) {

}
