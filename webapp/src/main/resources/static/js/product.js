document.addEventListener('alpine:init', () => {
    Alpine.data('initData', (page) => ({
        pageNum: page,
        products: {
            data: []
        }, init() {
            this.loadProducts(this.pageNum)
        }, loadProducts(pageNo) {
            $.getJSON(apiGatewayUrl+"/catalog/api/products?page=" + pageNo, (resp) => {
                this.products = resp;
            });
        },
        addToCart(product){
            addProductToCart(product)
        }
    }))
})
