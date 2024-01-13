package com.dissertation.subtrackerbackend.domain.mapper;
import com.dissertation.subtrackerbackend.domain.Transaction;
import com.dissertation.subtrackerbackend.domain.User;
import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;
import com.dissertation.subtrackerbackend.domain.dto.UserDTO;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {SubscriptionMapper.class}
)
public interface TransactionMapper extends BaseMapper<Transaction, TransactionDTO> {

    @Override
    public abstract Transaction toEntity(TransactionDTO userDTO);

    @Override
    public abstract TransactionDTO toDto(Transaction user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateTransactionFromDto(@MappingTarget Transaction transaction, TransactionDTO transactionDTO);

}