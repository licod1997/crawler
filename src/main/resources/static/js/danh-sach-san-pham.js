_( document ).ready( function () {
    var obj = {
        manufacture: null,
        socket: "FCLGA3647",
        type: null,
        noOfCores: null,
        page: "1",
        maxSize: "20",
        totalPage: null,
        sort: "DESC",
        field: "benchmark"
    }


    _.ajax( {
        url: 'http://localhost:8080/danh-sach-san-pham.xml',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        data: JSON.stringify( obj ),
        async: true,
        success: function ( xhr ) {
            var response, parser, xml;
            response = xhr.responseText;
            parser = new DOMParser();
            xml = parser.parseFromString( response, 'text/xml' );

            console.log( xml.getElementsByTagName( 'first' )[0].childNodes[0].nodeValue );
        },
        error: function () {

        }
    } );

    function collapse( selector ) {
        var icon = _( selector + ' .fas' );
        var content = _( selector + ' .collapse' );

        if ( icon.hasClass( 'fa-chevron-down' ) ) {
            icon.removeClass( 'fa-chevron-down' );
            icon.addClass( 'fa-chevron-up' );
        } else {
            icon.removeClass( 'fa-chevron-up' );
            icon.addClass( 'fa-chevron-down' );
        }

        var panel = content.element;
        if ( panel.style.maxHeight ) {
            panel.style.maxHeight = null;
        } else {
            panel.style.maxHeight = panel.scrollHeight + "px";
        }
    }

    _( '#card-1 .card-header' ).on( 'click', function () {
        collapse( '#card-1' );
    } );

    _( '#card-2 .card-header' ).on( 'click', function () {
        collapse( '#card-2' );
    } );

    _( '#card-3 .card-header' ).on( 'click', function () {
        collapse( '#card-3' );
    } );

    _( '#card-4 .card-header' ).on( 'click', function () {
        collapse( '#card-4' );
    } );

    _( '#card-5 .card-header' ).on( 'click', function () {
        collapse( '#card-5' );
    } );
} );

