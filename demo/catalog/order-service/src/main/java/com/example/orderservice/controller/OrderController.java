package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class OrderController {
    private final OrderService orderService;
    private final Environment env;

    public OrderController(OrderService orderService, Environment env) {
        this.orderService = orderService;
        this.env = env;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody RequestOrder requestOrder, @PathVariable("userId") String userId){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createOrder = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(createOrder, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@RequestBody RequestOrder requestOrder, @PathVariable("userId") String userId){
        Iterable<OrderEntity> orderList =  orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();

        orderList.forEach(v -> result.add(new ModelMapper().map(v, ResponseOrder.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
