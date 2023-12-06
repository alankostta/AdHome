/**
 * Deshboad resultados
 */
 const ctx = document.getElementById('lineChart');

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Julh', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
      datasets: [{
        label: 'Faturamento em R$',
        data: [15000, 19000, 30000, 50000, 20000, 30000, 60000, 80000, 70000, 65000, 100000, 110000],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
  
  const ct = document.getElementById('lineChart2');

  new Chart(ct, {
    type: 'line',
    data: {
      labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Julh', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
      datasets: [{
        label: 'Clientes Cadastrados',
        data: [10, 88, 50, 30, 50, 25, 10, 5, 30, 45, 70, 103],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });