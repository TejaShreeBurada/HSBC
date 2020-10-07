package com.assessment.hsbc.feignclient.bind;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Root{
    public String base;
    public Rates rates;
    public String date;
}

