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
        /////   -----------------------
    }
}(jQuery));
$(document).ready(function () {
    $.when(toy.getAgeCombo())
        .done(function (response) {
            var $select = $('#ageCombo');
            $select.append("<option value=''>All</option>");
            $.each(response, function (index, value) {
                $select.append('<option value=' + value.value + '>' + value.text +
                    '</option>');
            });
        });
    $.when(toy.getGenderCombo())
        .done(function (response) {
            var $select = $('#genderCombo');
            $select.append("<option value=''>All</option>");
            $.each(response, function (index, value) {
                $select.append('<option value=' + value.value + '>' + value.text +
                    '</option>');
            });
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
});
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>End document ready>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//]]>