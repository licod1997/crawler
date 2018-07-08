_( document ).ready( function () {
    var obj = {
        manufacture: null,
        socket: null,
        type: null,
        noOfCores: null,
        page: "1",
        maxSize: "20",
        totalPage: null,
        sort: "DESC",
        field: "benchmark",
        buyable: true,
        price: null,
    }

    var xml, xsl, compareXML;

    _.ajax( {
        url: 'http://localhost:8080/xsl/danh-sach-san-pham.xsl',
        method: 'GET',
        async: false,
        success: function ( xhr ) {
            xsl = xhr.responseXML;
        },
        error: function () {

        }
    } );

    function getList() {
        _.ajax( {
            url: 'http://localhost:8080/danh-sach-san-pham.xml',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify( obj ),
            async: false,
            success: function ( xhr ) {
                xml = xhr.responseXML;
            },
            error: function () {

            }
        } );
    }

    function displayResult( xml, xsl ) {
        if ( document.implementation && document.implementation.createDocument ) {
            var xsltProcessor = new XSLTProcessor();
            xsltProcessor.importStylesheet( xsl );
            var resultDocument = xsltProcessor.transformToFragment( xml, document );
            document.getElementById( "list-content" ).appendChild( resultDocument );
        }
    }

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

    function clickedPage( i ) {
        if ( i !== '' ) {
            obj.page = i;
            _( '.pagination' ).html().innerHTML = '';
            _( '#list-content' ).html().innerHTML = '';
            getList();
            displayResult( xml, xsl );
            resolvePaging();
            addCompareProductEvent();
        }
    }

    function resolvePaging() {
        var isFirst = xml.getElementsByTagName( 'first' )[0].childNodes[0].nodeValue;
        var isLast = xml.getElementsByTagName( 'last' )[0].childNodes[0].nodeValue;
        var totalPages = xml.getElementsByTagName( 'totalPages' )[0].childNodes[0].nodeValue;
        var number = ++xml.getElementsByTagName( 'number' )[0].childNodes[0].nodeValue;
        var pagination = _( '.pagination' ).html();

        var i, start, end;

        if ( totalPages < 1 ) return;

        if ( number <= 3 ) {
            start = 1;
            end = 5;
        } else if ( number >= totalPages - 2 ) {
            start = totalPages - 5;
            end = totalPages;
        } else {
            start = number - 2;
            end = number + 2;
        }
        if ( totalPages < 5 ) {
            start = 1;
            end = totalPages;
        }
        pagination.innerHTML +=
            '<li class="page-item' + (isFirst === 'true' ? ' disabled' : '') + '">\n' +
            '   <a class="page-link" href="' + (isFirst === 'true' ? '' : '1') + '">\n' +
            '       <i class="fas fa-angle-double-left"></i>\n' +
            '   </a>\n' +
            '</li>\n' +
            '<li class="page-item' + (isFirst === 'true' ? ' disabled' : '') + '">\n' +
            '   <a class="page-link" href="' + (isFirst === 'true' ? '' : number - 1) + '">\n' +
            '       <i class="fas fa-angle-left"></i>\n' +
            '   </a>\n' +
            '</li>';

        for ( i = start; i <= end; i++ ) {
            pagination.innerHTML +=
                '<li class="page-item' + (i === number ? ' active' : '') + '">\n' +
                '   <a class="page-link" href="' + (i === number ? '' : i) + '">' + i + '</a>\n' +
                '</li>';
        }

        pagination.innerHTML +=
            '<li class="page-item' + (isLast === 'true' ? ' disabled' : '') + '">\n' +
            '   <a class="page-link" href="' + (isLast === 'true' ? '' : number + 1) + '">\n' +
            '       <i class="fas fa-angle-right"></i>\n' +
            '   </a>\n' +
            '</li>\n' +
            '<li class="page-item' + (isLast === 'true' ? ' disabled' : '') + '">\n' +
            '   <a class="page-link" href="' + (isLast === 'true' ? '' : totalPages) + '">\n' +
            '       <i class="fas fa-angle-double-right"></i>\n' +
            '   </a>\n' +
            '</li>';

        var link = pagination.querySelectorAll( '.page-link' );

        for ( i = 0; i < link.length; i++ ) {
            link[i].addEventListener( 'click', function ( e ) {
                e.preventDefault();
                clickedPage( this.getAttribute( 'href' ) );
            } );
        }
    }

    function filter() {
        var sort, field, buyable, manufacture, price, type, socket, noOfCores, i;
        var options = _( '#filter' ).html().querySelectorAll( 'input' );

        manufacture = [];
        socket = [];
        price = [];
        noOfCores = [];
        type = [];

        for ( i = 0; i < options.length; i++ ) {
            if ( options[i].getAttribute( 'name' ) === 'sort' && options[i].checked ) {
                sort = options[i].getAttribute( 'value' );
            }
            if ( options[i].getAttribute( 'name' ) === 'field' && options[i].checked ) {
                field = options[i].getAttribute( 'value' );
            }
            if ( options[i].getAttribute( 'name' ) === 'buyable' && options[i].checked ) {
                options[i].getAttribute( 'value' ) === 'true' ? buyable = 1 : buyable = 0;
            }
            if ( options[i].getAttribute( 'name' ) === 'manufacture' && options[i].checked ) {
                manufacture.push( options[i].getAttribute( 'value' ) );
            }
            if ( options[i].getAttribute( 'name' ) === 'price' && options[i].checked ) {
                price.push( options[i].getAttribute( 'value' ) );
            }
            if ( options[i].getAttribute( 'name' ) === 'type' && options[i].checked ) {
                type.push( options[i].getAttribute( 'value' ) );
            }
            if ( options[i].getAttribute( 'name' ) === 'socket' && options[i].checked ) {
                socket.push( options[i].getAttribute( 'value' ) );
            }
            if ( options[i].getAttribute( 'name' ) === 'no-of-cores' && options[i].checked ) {
                noOfCores.push( +options[i].getAttribute( 'value' ) );
            }
        }

        obj.sort = sort;
        obj.field = field;
        obj.buyable = buyable;
        obj.manufacture = manufacture;
        obj.price = price;
        obj.type = type;
        obj.socket = socket;
        obj.noOfCores = noOfCores;
    }

    function addCompareProductEvent() {
        var adds = document.querySelectorAll( '.compare' );
        var i;

        for ( i = 0; i < adds.length; i++ ) {
            adds[i].addEventListener( 'click', function () {
                if ( compareXML == null || compareXML.getElementsByTagName( 'product' ).length < 4 ) {
                    _.ajax( {
                        url: 'http://localhost:8080/them-san-pham-vao-so-sanh.xml?id=' + this.getAttribute( 'value' ),
                        method: 'GET',
                        async: false,
                        success: function ( xhr ) {
                            compareXML = xhr.responseXML;
                            numberOfComparingProduct( compareXML );
                        },
                        error: function () {

                        }
                    } );
                }
            } );
        }
    }

    var inputs = _( '#filter' ).html().querySelectorAll( 'input' );

    for ( i = 0; i < inputs.length; i++ ) {
        inputs[i].addEventListener( 'click', function () {
            _( '.pagination' ).html().innerHTML = '';
            _( '#list-content' ).html().innerHTML = '';
            filter();
            getList();
            displayResult( xml, xsl );
            resolvePaging();
            addCompareProductEvent();
        } );
    }

    _( '#card-0 .card-header' ).on( 'click', function () {
        collapse( '#card-0' );
    } );

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

    var searchXML, searchXSL;

    _.ajax( {
        url: 'http://localhost:8080/xsl/top-10-san-pham.xsl',
        method: 'GET',
        async: false,
        success: function ( xhr ) {
            searchXSL = xhr.responseXML;
        },
        error: function () {

        }
    } );

    function getSearchList( value ) {
        _.ajax( {
            url: 'http://localhost:8080/goi-y-san-pham.xml?name=' + value,
            method: 'GET',
            async: false,
            success: function ( xhr ) {
                searchXML = xhr.responseXML;

            },
            error: function () {

            }
        } );
    }

    function displaySearchResult( xml, xsl ) {
        if ( document.implementation && document.implementation.createDocument ) {
            var xsltProcessor = new XSLTProcessor();
            xsltProcessor.importStylesheet( xsl );
            var resultDocument = xsltProcessor.transformToFragment( xml, document );
            var displayer = _( '#suggest-list' ).html();
            displayer.innerHTML = '';
            displayer.appendChild( resultDocument );
            var dropdown = _( '#suggest-list .dropdown-menu' ).html();
            if ( dropdown != null ) {
                dropdown.style.display = 'block';
                dropdown.style.transition = 'display 10s ease-in-out';
            }
        }
    }

    _( '#search-input' ).on( 'keyup', function () {
        getSearchList( this.value );
        displaySearchResult( searchXML, searchXSL );
    } );

    _( '#to-compare-page' ).on( 'click', function () {
        window.location.href = '/so-sanh-san-pham';
    } );

    function triggerDropdown() {
        var drops = _( '#nav-2' ).html().querySelectorAll( '.dropdown' );
        var i;

        for ( i = 0; i < drops.length; i++ ) {
            drops[i].addEventListener( 'mouseover', function () {
                this.querySelector( '.dropdown-menu' ).classList.remove( 'show' );
                this.querySelector( '.dropdown-menu' ).classList.add( 'show' );
            } );
            drops[i].addEventListener( 'mouseout', function () {
                this.querySelector( '.dropdown-menu' ).classList.remove( 'show' );
            } );
        }
    }

    function numberOfComparingProduct( xml ) {
        _( '#to-compare-page .badge' ).html().innerHTML = xml.getElementsByTagName( 'product' ).length;
    }

    filter();
    getList();
    displayResult( xml, xsl );
    resolvePaging();
    addCompareProductEvent();
    triggerDropdown();
} );

