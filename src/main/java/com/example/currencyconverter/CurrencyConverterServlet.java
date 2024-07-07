package com.example.currencyconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/convert")
public class CurrencyConverterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read JSON data from the request body
        BufferedReader reader = request.getReader();
        StringBuilder jsonBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }

        // Parse JSON data
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestData = mapper.readValue(jsonBody.toString(), HashMap.class);

        // Retrieve values from JSON data
        double amount = Double.parseDouble(requestData.get("amount"));
        String fromCurrency = requestData.get("from");
        String toCurrency = requestData.get("to");

        // Perform currency conversion (dummy logic, replace with actual conversion logic)
        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

        // Prepare response JSON
        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("convertedAmount", convertedAmount);

        // Send JSON response back to the client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
    }

    // Dummy conversion logic, replace with actual logic
    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // Replace with actual conversion rates or API call
        return amount * 1.1; // Dummy conversion rate
    }
}
