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
        /////   -----------------------
        populateToyResultTable: function (datalist) {
            $('#tableToyResult').DataTable({
                "paging": false,
                "data": datalist,
                "columns": [
                    // {
                    //     "data": "toyId",
                    //     "className": "dt-body-center"
                    // },
                    {
                        "data": "name",
                        "className": "dt-body-left",
                        "render": function (data, type, row) {
                            return ('<a href="#" onclick="return toy.openToyName(\'' + row.toyId + '\');">' + data + '</a>');
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
                            $('#shoppingCart').fadeIn(500);
                        });
                    });
            }
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
});
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>End document ready>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//]]>