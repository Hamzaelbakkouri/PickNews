package ma.youcode.candlelight.exceptions;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import graphql.GraphQLError;

@ControllerAdvice
public class GlobalExceptionsHandler {
    

    @GraphQlExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GraphQLError handleIlegalException(MethodArgumentNotValidException ex) {
        return GraphQLError.newError()
                            .message(ex.getMessage())
                            .build();
    }

    @GraphQlExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GraphQLError handleResourceNotFoundException(ResourceNotFound ex) {
        return GraphQLError.newError()
                            .message(ex.getMessage())
                            .build();
    }

    @GraphQlExceptionHandler(ResourceAlreadyExist.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public GraphQLError handleResourceAlreadyExistException(ResourceAlreadyExist ex) {
        return GraphQLError.newError()
                            .message(ex.getMessage())
                            .build();
    }

    @GraphQlExceptionHandler(InvalidCredentials.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public GraphQLError handleInvalidCredentialsException(InvalidCredentials ex) {
        return GraphQLError.newError()
                            .message(ex.getMessage())
                            .build();
    }

    @GraphQlExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GraphQLError handleRunTimeException(RuntimeException ex) {
        return GraphQLError.newError()
                            .message(ex.getMessage())
                            .build();
    }
    

}
