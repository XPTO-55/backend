package br.com.cpa.spring.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {
  private String from;
  private String to;
  private String subject;
  private Map<String, Object> model = new HashMap<>();
}
