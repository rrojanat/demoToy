//<![CDATA[
var toy = (function ($) {
    return {
        getAgeCombo: function () {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": "/rest/agecombo",
                "method": "GET",
                "contentType": "application/json; charset=utf-8"
            });
        },
        getGenderCombo: function () {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": "/rest/gendercombo",
                "method": "GET",
                "contentType": "application/json; charset=utf-8"
            });
        },
        searchToys: function (criteria) {
            console.log(criteria);
            return $.ajax({
                "async": true,
                "cache": false,
                "url": "/rest/toy",
                "method": "POST",
                "contentType": "application/json; charset=utf-8",
                data: JSON.stringify(criteria)
            });
        },
        getToyById: function (toyId) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/toy/' + toyId,
                "method": "GET",
                "contentType": "application/json; charset=utf-8"
            });
        },
        addNewCart: function () {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart',
                "method": "PUT",
                "contentType": "application/json; charset=utf-8"
            });
        },
        updateQty: function (cartDetailId, qty) {
            var cartDetail = {};
            cartDetail.qty = qty;
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/detail/' + cartDetailId + "/qty",
                "method": "PUT",
                "contentType": "application/json; charset=utf-8",
                data: JSON.stringify(cartDetail)
            });
        },
        addNewCartDetail: function (cartDetail) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/detail',
                "method": "PUT",
                "contentType": "application/json; charset=utf-8",
                data: JSON.stringify(cartDetail)
            });
        },
        getCart: function (cartId) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/' + cartId,
                "method": "GET",
                "contentType": "application/json; charset=utf-8"
            });
        },
        getCartDetailByCartId: function (cartId) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/' + cartId + "/detail",
                "method": "GET",
                "contentType": "application/json; charset=utf-8"
            });
        },
        getDeleteCartDetail: function (cartDetailId) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/detail/' + cartDetailId,
                "method": "DELETE",
                "contentType": "application/json; charset=utf-8"
            });
        },
        confirmDelivery: function (cartData) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/' + cartData.cartId + '/address',
                "method": "PUT",
                "contentType": "application/json; charset=utf-8",
                data: JSON.stringify(cartData)
            });
        },
        updateShoppingPrice: function (cartId) {
            return $.ajax({
                "async": true,
                "cache": false,
                "url": '/rest/cart/' + cartId + '/price',
                "method": "PUT",
                "contentType": "application/json; charset=utf-8"
            });
        },
        /////   -----------------------
        populateToyResultTable: function (datalist) {
            $('#tableToyResult').DataTable({
                "paging": false,
                "data": datalist,
                "columns": [{
                        "data": "name",
                        "className": "dt-body-left",
                        "render": function (data, type, row) {
                            if (row.qty > 0) {
                                return ('<a href="#" onclick="return toy.openToyName(\'' + row.toyId + '\');">' + data + '</a>');
                            }
                            return data;
                        }
                    },
                    {
                        "data": "gender",
                        "className": "dt-body-left"
                    },
                    {
                        "data": "age",
                        "className": "dt-body-left"
                    },
                    {
                        "data": "stockStatus",
                        "className": "dt-body-center"
                    }
                ]
            });
        },
        openToyName: function (toyId) {
            $.when(toy.getToyById(toyId))
                .done(function (response) {
                    $('#2toyId').val(response.toyId);
                    $('#2toyName').text(response.name);
                    $('#2toyBrand').text(response.brand);
                    $('#2toyGender').text(response.gender);
                    $('#2toyAge').text(response.age);
                    $('#2toyPrice').text(response.price);
                    $('#2toyShipping').text(response.shippingMethod);
                    $('#2toyStock').text(response.stockStatus);
                    $('#2toyStockQty').text(response.qty);
                    var selectedtoyQty = $('#2toyQty');
                    selectedtoyQty.empty();
                    for (var ii = 1; ii <= response.qty; ii++) {
                        selectedtoyQty.append('<option value=' + ii + '>' + ii + '</option>');
                    }
                    $('#2toyImg').attr('src', response.toyImg);
                    $('#searchToy').fadeOut(500, function () {
                        $('#toyName').fadeIn(500);
                    });
                });
        },
        addToCart: function (cartId, toyId) {
            var cartDetail = {};
            if (cartId == null || cartId == '') {
                $.when(toy.addNewCart())
                    .done(function (resCart) {
                        cartDetail.cartId = resCart.cartId;
                        $('#cartId').val(resCart.cartId);
                        cartDetail.toyId = toyId;
                        cartDetail.qty = $('#2toyQty option:selected').val();
                        $.when(toy.addNewCartDetail(cartDetail))
                            .done(function (resCartDetail) {
                                $('#toyName').fadeOut(500, function () {
                                    toy.renderShoppingCart(resCart.cartId);
                                    $('#shoppingCart').fadeIn(500);
                                });
                            });
                    });
            } else {
                cartDetail.cartId = cartId;
                cartDetail.toyId = toyId;
                cartDetail.qty = $('#2toyQty option:selected').val();
                $.when(toy.addNewCartDetail(cartDetail))
                    .done(function (resCartDetail) {
                        $('#toyName').fadeOut(500, function () {
                            toy.renderShoppingCart(cartId);
                            $('#shoppingCart').fadeIn(500);
                        });
                    });
            }
        },
        renderShoppingCart: function (cartId) {
            $.when(toy.getCart(cartId), toy.getCartDetailByCartId(cartId))
                .done(function (respCart, respCartDetail) {
                    toy.populateShoppingCartDetail(respCart[0], respCartDetail[0]);
                });
        },
        onUpdateQty: function (cartDetailId, selectObj) {
            console.log(cartDetailId);
            console.log($(selectObj).val());
            $.when(toy.updateQty(cartDetailId, $(selectObj).val()))
                .done(function (resp) {
                    var cartId = $('#cartId').val();
                    toy.renderShoppingCart(cartId);
                    return false;
                });
        },
        populateShoppingCartDetail: function (respCart, cartDetails) {
            if ($.fn.DataTable.isDataTable('#tableShoppingCart')) {
                $('#tableShoppingCart').DataTable().destroy();
            }
            if (cartDetails == null || cartDetails.length < 1) {
                $('#ProcessCheckOut').hide();
            } else {
                $('#ProcessCheckOut').show();
            }
            $('#tableShoppingCart').DataTable({
                "searching": false,
                "info": false,
                "ordering": false,
                "destroy": true,
                "paging": false,
                "data": cartDetails,
                "columns": [{
                        "data": "cartDetailId",
                        "className": "dt-body-left",
                        "render": function (data, type, row) {
                            var items = row.name + " By " + row.brand + "<BR>";
                            items += row.gender + "<BR>";
                            items += row.age + "<BR>";
                            items += row.stockStatus + "<BR>";
                            items += "<button class='ui primary button' onclick='return toy.deleteToyFromShoppingCart(" +
                                respCart.cartId + "," + row.cartDetailId + ")'>Delete</button>";
                            console.log(items);
                            return items;
                        }
                    },
                    {
                        "data": "qty",
                        "className": "dt-body-center",
                        "render": function (data, type, row) {
                            console.log(row.stockQty);
                            var selectQty = "<select name='selectedQty' onChange='return toy.onUpdateQty(" + row.cartDetailId + ",this);'>";
                            var selected = ""

                            for (var ii = 1; ii <= row.stockQty; ii++) {
                                if (ii == data) {
                                    selected = "selected";
                                } else {
                                    selected = "";
                                }
                                selectQty += "<option value='" + ii + "' " + selected + ">" + ii + "</option>"
                            }
                            selectQty += "</select>";
                            return selectQty;
                        }
                    },
                    {
                        "data": "detailPrice",
                        "className": "dt-body-right",
                        "render": function (data, type, row) {
                            return numeral(data).format('0,0.00')
                        }
                    }
                ],
                "footerCallback": function (row, data, start, end, display) {
                    var api = this.api();
                    console.log(data);
                    var numberOfItems = data.length;
                    var itemLabel = numberOfItems > 1 ? "Items" : "Item";
                    // shoping method
                    // Update footer
                    $(api.column(0).footer()).html(
                        'Shoping Method: Cash on Delivery <br>' +
                        'Shooping Fee: 50.00 THB'
                    );
                    $(api.column(2).footer()).html(
                        'Sub Total (' + numeral(numberOfItems).format('0,0') + ' ' + itemLabel + '): ' + numeral(respCart.subTotal).format('0,0.00') + " THB<br>" +
                        'Shooping Fee: 50.00 THB<br>' +
                        'Total: ' + numeral(respCart.total).format('0,0.00') + ' THB'
                    );
                },
            });
        },
        deleteToyFromShoppingCart: function (cartId, cartDetailId) {
            $.when(toy.getDeleteCartDetail(cartDetailId))
                .done(function (respDeletedCartDetail) {
                    toy.renderShoppingCart(cartId);
                });
        },
        /////   -----------------------
    }
}(jQuery));
$(document).ready(function () {
    $.when(toy.getAgeCombo(), toy.getGenderCombo())
        .done(function (resAge, resGender) {
            var ageSelect = $('#ageCombo');
            ageSelect.append("<option value=''>All</option>");
            $.each(resAge[0], function (index, value) {
                ageSelect.append('<option value=' + value.value + '>' + value.text +
                    '</option>');
            });
            var genderSelect = $('#genderCombo');
            genderSelect.append("<option value=''>All</option>");
            $.each(resGender[0], function (index, value) {
                genderSelect.append('<option value=' + value.value + '>' + value.text +
                    '</option>');
            });
            $("#searchButton").trigger('click');
        });
    $('#searchResult').hide();


    //  -------------------------- button -------------------------------------------
    $("#searchButton").click(function () {
        var criteria = {};
        criteria.searchAge = $('#ageCombo').val();
        criteria.searchGender = $('#genderCombo').val();
        $.when(toy.searchToys(criteria))
            .done(function (response) {
                console.log(response);
                if (!$.fn.DataTable.isDataTable('#tableToyResult')) {
                    toy.populateToyResultTable(response);
                } else {
                    var table = $('#tableToyResult').DataTable();
                    table.rows().clear().draw();
                    table.rows.add(response).draw();
                }
                if (response != null && response.length > 0) {
                    $('#searchResult').show();
                }
            });
    });
    $('#AddToCart').click(function () {
        var cartId = $('#cartId').val();
        var toyId = $('#2toyId').val();
        toy.addToCart(cartId, toyId);
    });
    $('#ProcessCheckOut').click(function () {
        var cartId = $('#cartId').val();
        $.when(toy.updateShoppingPrice(cartId))
            .done(function (resp) {
                $('#shoppingCart').fadeOut(100, function () {
                    $('#shippingAddress').fadeIn(20);
                });
            });
    });
    $('#ContinueShopping').click(function () {
        $('#shoppingCart').fadeOut(100, function () {
            $('#searchToy').fadeIn(20);
            $("#searchButton").trigger('click');
        });
    });
    $('#4Confirm').click(function () {
        var cartId = $('#cartId').val();
        var cart = {};
        cart.cartId = cartId;
        cart.shoppingName = $('#4FullName').val();
        cart.addr1 = $('#4Address1').val();
        cart.addr2 = $('#4Address2').val();
        cart.city = $('#4City').val();
        cart.province = $('#4Province').val();
        cart.postcode = $('#4Postcode').val();
        $.when(toy.confirmDelivery(cart))
            .done(function (respData) {
                $('#shippingAddress').fadeOut(100, function () {
                    $('#summary').fadeIn(20);
                });
            });
    });
});
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>End document ready>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//]]>