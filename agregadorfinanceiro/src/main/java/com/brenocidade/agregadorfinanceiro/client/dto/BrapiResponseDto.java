package com.brenocidade.agregadorfinanceiro.client.dto;

import com.brenocidade.agregadorfinanceiro.controller.dto.AccountStockResponseDto;

import java.util.List;

public record BrapiResponseDto(List<StockDto> results) {
}
