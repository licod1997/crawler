_( document ).ready( function () {
    var searchXML, searchXSL, compareXML, xml, xsl;

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
                } else {
                    alert( 'Chỉ có thể so sánh tối đa 4 sản phẩm. Vui lòng xóa bớt sản phẩm trong phần so sánh' );
                }
            } );
        }
    }

    function numberOfComparingProduct( xml ) {
        _( '#to-compare-page .badge' ).html().innerHTML = xml.getElementsByTagName( 'product' ).length;
    }

    triggerDropdown();
    addCompareProductEvent();
} );