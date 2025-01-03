package tsi.daw.museumscheduling.interfaces;

public interface Messages {

    String VALIDATION_NAME_REQUIRED = "O nome é obrigatório.";
    String VALIDATION_CPF_REQUIRED = "O CPF é obrigatório.";
    String VALIDATION_CPF_FORMAT = "O CPF deve estar no formato 111.111.111-11";
    String VALIDATION_EMAIL_REQUIRED = "O e-mail é obrigatório.";
    String VALIDATION_EMAIL_INVALID = "E-mail inválido.";
    String VALIDATION_PASSWORD_REQUIRED = "A senha é obrigatória.";
    String VALIDATION_PASSWORD_LENGTH = "A senha deve ter no mínimo 8 caracteres.";
    String VALIDATION_USER_PROFILE_REQUIRED = "O perfil do usuário é obrigatório.";
    String VALIDATION_DATE_REQUIRED = "A data é obrigatória.";
    String VALIDATION_TIME_REQUIRED = "O horário é obrigatório.";
    String VALIDATION_TERM_CONSENT_REQUIRED = "O consentimento do termo é obrigatório.";
    String VALIDATION_TICKET_TYPE_REQUIRED = "O tipo de ingresso é obrigatório.";
    String VALIDATION_OPENING_TIME_REQUIRED = "O horário de abertura é obrigatório";
    String VALIDATION_CLOSING_TIME_REQUIRED = "O horário de fechamento é obrigatório";
    String VALIDATION_LIMIT_PEOPLE = "O limite de pessoas deve ser no mínimo 1";
    String VALIDATION_CODE_CONFIRMATION = "O código de confirmação é obrigatório";
    String VALIDATION_RESERVED_PEOPLE = "O número reservado de pessoas deve ser no mínimo 0.";
    String VALIDATION_PEOPLE_PRESENT = "O número de pessoas presentes deve ser maior ou igual a zero.";
    String VALIDATION_MUSEUM = "O museu é obrigatório";
    
    String REGEX_EXPRESSION_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
}
