_( document ).ready( function () {
    calcMarkPerPrice();
    addDetailEvent();

    function calcMarkPerPrice() {
        var benchmarks = document.querySelectorAll( '.benchmark' );
        var prices = document.querySelectorAll( '.price' );
        var mark_per_price = document.querySelectorAll( '.benchmark-per-price' );
        var i;
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
            colorize( clockspeeds[i], clockspeeds[0] );
            colorize( turbospeeds[i], turbospeeds[0] );
            colorize( no_of_cores[i], no_of_cores[0] );
            colorize( TDPs[i], TDPs[0] );
            colorize( benchmarks[i], benchmarks[0] );
            colorize( prices[i], prices[0] );
            colorize( mark_per_price[i], mark_per_price[0] );
        }
    }

    function colorize( obj1, obj2 ) {
        var value1 = +obj1.getAttribute( 'value' );
        var value2 = +obj2.getAttribute( 'value' );
        if ( isNaN( value1 ) ) {
            value1 = 0;
        }
        if ( isNaN( value2 ) ) {
            value2 = 0;
        }
        if ( value1 < value2 ) {
            obj1.classList.add( 'alert' );
            obj1.classList.add( 'alert-danger' );
        }
        if ( value1 > value2 ) {
            obj1.classList.add( 'alert' );
            obj1.classList.add( 'alert-success' );
        }
    }

    function addDetailEvent() {
        var details = document.querySelectorAll( '.product-detail button' );
        var i;

        for ( i = 0; i < details.length; i++ ) {
            details[i].addEventListener( 'click', function () {
                window.location.href = '/chi-tiet-san-pham?id=' + this.getAttribute('value');
            } );
        }
    }
} );