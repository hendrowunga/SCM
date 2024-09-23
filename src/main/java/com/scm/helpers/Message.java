package com.scm.helpers;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String content;
    private MessageType type=MessageType.blue;

}
