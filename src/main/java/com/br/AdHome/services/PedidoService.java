package com.br.AdHome.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.AdHome.models.Pedido;
import com.br.AdHome.repositories.PedidoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

	private String[] cols = { "id", "dataAlteraPedido", "anoRef", "valorPedido", "descontoPedido", "observacaoPedido",
			"dataCadastro", "enumStatus", "enumPagamento", "enumCartao", "itens", "user", "cliente" };
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Optional<Pedido> findById(Long id) {
		return pedidoRepository.findById(id);
	}

	public void deletePedido(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}

	public Map<String, Object> execute(PedidoRepository pedidoRepository, HttpServletRequest request) {
		int start = 0;
		int length = 10; // Valores padrão, você pode ajustá-los conforme necessário.
		int draw = 0;

		if (request.getParameter("start") != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		if (request.getParameter("length") != null) {
			length = Integer.parseInt(request.getParameter("length"));
		}
		if (request.getParameter("draw") != null) {
			draw = Integer.parseInt(request.getParameter("draw"));
		}

		start = Integer.parseInt(request.getParameter("start"));
		length = Integer.parseInt(request.getParameter("length"));
		draw = Integer.parseInt(request.getParameter("draw"));
		int current = currentPage(start, length);
		String column = columnName(request);
		Sort.Direction direction = orderBy(request);
		Pageable pageable = PageRequest.of(current, length, direction, column);

		Page<Pedido> page = queryBy(pedidoRepository, pageable);

		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());

		return json;
	}

	private Page<Pedido> queryBy(PedidoRepository pedidoRepository, Pageable pageable) {
		return pedidoRepository.findAll(pageable);
	}

	private String columnName(HttpServletRequest request) {
		int iCol = Integer.parseInt(request.getParameter("order[0][column]"));
		return cols[iCol];
	}

	private int currentPage(int start, int lenght) {
		// 0 , 1, 2
		// 0-9 | 10-19 | 20-29
		return start / lenght;
	}

	private Direction orderBy(HttpServletRequest request) {
		String order = request.getParameter("order[0][dir]");
		Sort.Direction sort = Sort.Direction.ASC;
		if (order.equalsIgnoreCase("desc")) {
			sort = Sort.Direction.DESC;
		}
		return sort;
	}

}
