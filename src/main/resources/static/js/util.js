/**
 * 
 */
document.addEventListener("DOMContentLoaded", async function() {
    let checkbox_sim = document.getElementById("simRadio");
    let checkbox_nao = document.getElementById("naoRadio");
    let button = document.querySelector("#btParce");

    checkbox_sim.addEventListener("click", async (e) => {
        // Operações assíncronas, se necessário
        await someAsyncOperation();
        
        button.removeAttribute("disabled");
    });

    checkbox_nao.addEventListener("click", async (e) => {
        // Operações assíncronas, se necessário
        await someAsyncOperation();
        
        button.setAttribute("disabled", true);
    });

    // Função assíncrona simulada
    async function someAsyncOperation() {
        return new Promise((resolve) => {
            setTimeout(() => {
                console.log("Async operation complete");
                resolve();
            }, 1000);
        });
    }
});