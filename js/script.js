var API_CONFIG = {
    BASE_URL: "http://localhost:8080/api/products"
};

document.addEventListener("DOMContentLoaded",() =>{


    // --- funções da api ---

    async function fetchData(){
        try{
            const response = await fetch(API_CONFIG.BASE_URL);
            if(! response.ok){
                throw new Error("Erro ao buscar produtos.");
            }

            const apiData = await response.json();

             return apiData;
        }catch(error){
            console.error(error);
            return [];
        }
    }


    async function saveProduct(product){
        try{
            const response = await fetch(API_CONFIG.BASE_URL,{
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body:JSON.stringify(product)
            });

            if(! response.ok) throw new Error("Erro ao cadastrar o produto.");

            return await response.json();
        }catch(error){
            console.error(error);
            alert("Erro ao cadastrar o produto.");
        }
    }

    async function removeProduct (id) {
        try{
            const response = await fetch(`${API_CONFIG.BASE_URL}/${id}`,{
                method:'DELETE'
            });

            if(! response.ok) throw new Error("Erro ao excluir o produto");
            return true;
        }catch(error){
            console.error(error);
            alert("Falha ao excluir o produto");
            return false;
        }
    }
    
    // --- Lógica da Página de CADASTRO (index.html) ---
    const productForm = document.getElementById("product-form");
    if(productForm){
        productForm.addEventListener('submit', async (event) =>{
            event.preventDefault();
            
            const newProduct = {
                productName : document.getElementById("product-name").value,
                productDescription : document.getElementById("product-description").value,
                productPrice : parseFloat(document.getElementById("product-price").value)
            };

            const createProduct =  await saveProduct(newProduct);
            if(createProduct){
                alert(`Produto ${createProduct.productName} criado com sucesso.`);
                productForm.reset();
            }
        });
    }

    // -- Lógica  da Página de visualização de (produtos.html) ---
    const productListDiv = document.getElementById("product-list");
    if(productListDiv){
        const renderProducts = async () => {
            productListDiv.innerHTML = "<p>Carregando produtoss...</p>";
            const products = await fetchData();
            productListDiv.innerHTML = "";

            if(products.length  === 0){
                productListDiv.innerHTML = "<p>Nenhum produto cadastrado ainda.</p>";
                return;
            }

            products.forEach(product =>{
                const item  = document.createElement("div");
                item.className = "product-item";

                item.innerHTML = `
                
                    <div>
                        <h3>${product.productName}</h3>
                        <p>${product.productDescription}</p>
                        <p class="price">R$ ${product.productPrice.toFixed(2).replace(".",",")}</p>
                    </div>
                    <button class="btn-delete" data-id="${product.productId}">
                        <i class="fas fa-trash"></i>
                    </button>
                `;
                // data-id = Recebee o  id do produto para conseguirmos deletar.
                productListDiv.appendChild(item);
            });
        };

        productListDiv.addEventListener('click', async (e) => {
            if (e.target.closest('.btn-delete')) {
                const button = e.target.closest('.btn-delete');
                const productId = button.getAttribute('data-id');
                
                if (confirm('Tem certeza que deseja excluir este produto?')) {
                    const sucesso = await removeProduct(productId);
                    if (sucesso) {
                        renderProducts(); // Atualiza a lista após excluir
                    }
                }
            }
        });

        renderProducts();
    }


})