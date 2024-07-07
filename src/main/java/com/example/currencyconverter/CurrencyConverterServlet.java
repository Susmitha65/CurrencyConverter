package com.example.currencyconverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterServlet extends HttpServlet {

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD_EUR", 0.85);
        exchangeRates.put("EUR_USD", 1.18);
        exchangeRates.put("USD_GBP", 0.75);
        exchangeRates.put("GBP_USD", 1.33);
        exchangeRates.put("USD_JPY", 110.00);
        exchangeRates.put("JPY_USD", 0.0091);
        exchangeRates.put("USD_CAD", 1.25);
        exchangeRates.put("CAD_USD", 0.80);
        exchangeRates.put("USD_AUD", 1.35);
        exchangeRates.put("AUD_USD", 0.74);
        exchangeRates.put("USD_INR", 74.00);
        exchangeRates.put("INR_USD", 0.013);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestMap = mapper.readValue(req.getInputStream(), Map.class);

        String from = requestMap.get("from");
        String to = requestMap.get("to");
        double amount = Double.parseDouble(requestMap.get("amount"));

        String conversionKey = from + "_" + to;
        double convertedAmount = amount * exchangeRates.getOrDefault(conversionKey, 1.0);

        Map<String, Double> responseMap = new HashMap<>();
        responseMap.put("convertedAmount", convertedAmount);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(resp.getOutputStream(), responseMap);
    }
}
