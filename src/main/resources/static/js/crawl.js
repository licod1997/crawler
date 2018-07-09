_( document ).ready( function () {
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
        window.location.href = 'http://localhost:8080/so-sanh-san-pham';
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

    triggerDropdown();
    var x;
    checkThread();

    function checkThread() {
        var flag = false;
        x = setInterval( function () {
            _.ajax( {
                url: 'http://localhost:8080/quan-tri-vien/kiem-tra-thread',
                method: 'GET',
                async: false,
                success: function ( xhr ) {
                    var response = xhr.responseText;
                    if ( response != 'Stopped' ) {
                        _( '.result' ).html().innerHTML = 'Đã tìm thấy ' + xhr.responseText + ' kết quả';
                        if ( !flag ) {
                            _( '.loader' ).html().style.display = 'block';
                            _( '.loader-text' ).html().style.display = 'block';
                            _( '#start' ).addClass( 'disabled' );
                            _( '#stop' ).removeClass( 'disabled' );
                        }
                        flag = true;
                    } else {
                        _( '.loader' ).html().style.display = 'none';
                        _( '.loader-text' ).html().style.display = 'none';
                        _( '#start' ).removeClass( 'disabled' );
                        _( '#stop' ).addClass( 'disabled' );
                        clearInterval( x );
                    }
                },
                error: function ( xhr ) {
                    clearInterval( x );
                }
            } );
        }, 1000 );
    }

    function startThread() {
        _( '.loader' ).html().style.display = 'block';
        _( '.loader-text' ).html().style.display = 'block';
        _( '#start' ).addClass( 'disabled' );
        _( '#stop' ).removeClass( 'disabled' );
        _.ajax( {
            url: 'http://localhost:8080/quan-tri-vien/bat-dau-crawl',
            method: 'GET',
            async: true,
            success: function () {

            },
            error: function () {

            }
        } );
    }

    function stopThread() {
        _.ajax( {
            url: 'http://localhost:8080/quan-tri-vien/dung-crawl',
            method: 'GET',
            async: false,
            success: function () {

            },
            error: function () {

            }
        } );
        clearInterval( x );
        _( '.loader' ).html().style.display = 'none';
        _( '.loader-text' ).html().style.display = 'none';
        _( '#start' ).removeClass( 'disabled' );
        _( '#stop' ).addClass( 'disabled' );
    }

    _( '#start' ).on( 'click', function () {
        if (this.classList.contains('disabled')) return;
        startThread();
        checkThread();
    } );

    _( '#stop' ).on( 'click', function () {
        if (this.classList.contains('disabled')) return;
        stopThread();
    } )
} );