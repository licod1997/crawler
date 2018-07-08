_( document ).ready( function () {

    var xml, xsl;

    _.ajax( {
        url: 'http://localhost:8080/xsl/so-sanh-san-pham.xsl',
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
            url: 'http://localhost:8080/so-sanh-san-pham.xml',
            method: 'GET',
            async: false,
            success: function ( xhr ) {
                xml = xhr.responseXML;
            },
            error: function () {

            }
        } );
    }

    function displayResult( xml, xsl ) {
        console.log( xsl );
        console.log( xml );
        if ( document.implementation && document.implementation.createDocument ) {
            var xsltProcessor = new XSLTProcessor();
            xsltProcessor.importStylesheet( xsl );
            var resultDocument = xsltProcessor.transformToFragment( xml, document );
            var table = _( '#compare-table' ).html();
            table.innerHTML = '';
            table.appendChild( resultDocument );
        }
    }

    function calcMarkPerPrice() {
        var benchmarks = document.querySelectorAll( '.benchmark' );
        var prices = document.querySelectorAll( '.price' );
        var mark_per_price = document.querySelectorAll( '.benchmark-per-price' );
        var i;
        if ( _( '#compare-table' ).html().querySelector( 'tr' ) != null ) {
            var td = _( '#compare-table' ).html().querySelector( 'tr' ).getElementsByTagName( 'td' ).length - 1;

            for ( i = 0; i < td; i++ ) {
                if ( benchmarks[i].getAttribute( 'value' ) != null ) {
                    if ( prices[i].getAttribute( 'value' ) != null ) {
                        var result = Math.round( (+benchmarks[i].getAttribute( 'value' ) / +prices[i].getAttribute( 'value' )) * 1000000 ) / 100;
                        mark_per_price[i].innerHTML = result;
                        mark_per_price[i].setAttribute( 'value', result );
                    }
                }
            }

            highlight();
        }
    }

    function highlight() {
        var td = _( '#compare-table' ).html().querySelector( 'tr' ).getElementsByTagName( 'td' ).length - 1;
        if ( td < 2 ) return;
        var clockspeeds = document.querySelectorAll( '.clockspeed' );
        var turbospeeds = document.querySelectorAll( '.turbospeed' );
        var no_of_cores = document.querySelectorAll( '.no-of-cores' );
        var TDPs = document.querySelectorAll( '.TDP' );
        var benchmarks = document.querySelectorAll( '.benchmark' );
        var prices = document.querySelectorAll( '.price' );
        var mark_per_price = document.querySelectorAll( '.benchmark-per-price' );
        var i;

        for ( i = 1; i < td; i++ ) {
            colorize( clockspeeds[i], clockspeeds[0], false );
            colorize( turbospeeds[i], turbospeeds[0], false );
            colorize( no_of_cores[i], no_of_cores[0], false );
            colorize( TDPs[i], TDPs[0], true );
            colorize( benchmarks[i], benchmarks[0], false );
            colorize( prices[i], prices[0], true );
            colorize( mark_per_price[i], mark_per_price[0], false );
        }
    }

    function colorize( obj1, obj2, reverse ) {
        var value1 = +obj1.getAttribute( 'value' );
        var value2 = +obj2.getAttribute( 'value' );
        if ( isNaN( value1 ) ) {
            value1 = 0;
        }
        if ( isNaN( value2 ) ) {
            value2 = 0;
        }
        if ( !reverse ) {
            if ( value1 < value2 ) {
                obj1.classList.add( 'alert' );
                obj1.classList.add( 'alert-danger' );
            }
            if ( value1 > value2 ) {
                obj1.classList.add( 'alert' );
                obj1.classList.add( 'alert-success' );
            }
        } else {
            if ( value1 > value2 ) {
                obj1.classList.add( 'alert' );
                obj1.classList.add( 'alert-danger' );
            }
            if ( value1 < value2 ) {
                obj1.classList.add( 'alert' );
                obj1.classList.add( 'alert-success' );
            }
        }
    }

    function addDetailEvent() {
        var details = document.querySelectorAll( '.product-detail button' );
        var i;

        for ( i = 0; i < details.length; i++ ) {
            details[i].addEventListener( 'click', function () {
                window.location.href = '/chi-tiet-san-pham?id=' + this.getAttribute( 'value' );
            } );
        }
    }

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
                console.log( searchXML );

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

    function addEventRemoveCompareProduct() {
        var removes = document.querySelectorAll( '.remove-item' );
        var i;

        for ( i = 0; i < removes.length; i++ ) {
            removes[i].addEventListener( 'click', function () {
                _.ajax( {
                    url: 'http://localhost:8080/xoa-san-pham-khoi-so-sanh.xml?id=' + this.getAttribute( 'value' ),
                    method: 'GET',
                    async: false,
                    success: function ( xhr ) {
                        xml = xhr.responseXML;
                        displayData();
                        numberOfComparingProduct( xml );
                    },
                    error: function () {

                    }
                } );
            } );
        }
    }

    function numberOfComparingProduct( xml ) {
        _( '#to-compare-page .badge' ).html().innerHTML = xml.getElementsByTagName( 'product' ).length;
    }

    function displayData() {
        displayResult( xml, xsl );
        calcMarkPerPrice();
        addDetailEvent();
        addEventRemoveCompareProduct();
    }

    getList();
    displayData();
    triggerDropdown();
} );