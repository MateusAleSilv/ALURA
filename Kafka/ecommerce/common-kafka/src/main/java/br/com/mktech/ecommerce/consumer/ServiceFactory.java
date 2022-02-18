package br.com.mktech.ecommerce.consumer;

public interface ServiceFactory<T> {
    ConsumerService<T> create() throws Exception;
}
