package com.example.serviceproducerm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private String messageId;
    private String message;
    private Date messageDate;

}
