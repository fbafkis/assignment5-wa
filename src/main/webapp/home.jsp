<%--
  Created by IntelliJ IDEA.
  User: FB
  Date: 30/12/2021
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>

    <link href="https://getbootstrap.com/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.1/examples/list-groups/list-groups.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class=" text-center">
    <h1>Home</h1>
    &nbsp;
    <a href="./index" class="btn btn-warning">Index</a>
</div>
<hr>
<h3 class=" d-flex justify-content-center">Create a booking</h3>
&nbsp;
<div class="container d-flex justify-content-center" id="newBookingContainer">

    <div id="newBookingDiv" class="w-50"
         style="border-style:solid; border-width: 1px; border-radius: 30px; padding: 20px;">
        <div id="newBookingLoading" class="text-center">
            <div class="spinner-border text-warning" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
        <form id="newBookingForm">
            <div class="form-floating">
                <input type="number" name="personsNumber" class="form-control" id="personsNumberInputValue"
                       placeholder="1" required>
                <label for="personsNumberInputValue">Number of persons</label>
            </div>
            &nbsp;
            <div class="form-floating">
                <input type="date" name="arrival" class="form-control" id="arrivalInputValue" placeholder="01/02/2021"
                       required>
                <label for="arrivalInputValue">Arrival</label>
            </div>
            &nbsp;
            <div class="form-floating">
                <input type="date" name="departure" class="form-control" id="departureInputValue"
                       placeholder="28/02/2021" required>
                <label for="departureInputValue">Departure</label>
            </div>

            &nbsp;
            <div id="newBookingButtonDiv" class="text-center">
                <button class="btn btn-warning" onclick="askForAvailableAccommodations()">Check availability</button>
            </div>
        </form>
        <div id="availableAccommodationsListDiv">
            <div class="list-group list-group-checkable" id="availableAccommodationsListGroup"
                 style="overflow-y: auto; max-height:330px">
            </div>
            <div class="text-center">
                <div class="btn-group">
                    <button class="btn btn-warning" onclick="preBook()">Select accommodation</button>
                    &nbsp;
                    <button class="btn btn-danger" onclick="resetNewBooking()">Search again</button>
                </div>
            </div>
        </div>
        <div id="apartmentPreBookDiv">

            <h3>Type: <strong>Apartment</strong></h3>
            <h3>Name: <strong id="apartmentPreBookName"></strong></h3>
            <h3>Total price: <strong id="apartmentPreBookPrice"></strong></h3>
            <h3>Total cleaning price: <strong id="apartmentPreBookCleaningPrice"></strong></h3>
            <h3>Arrival: <strong id="apartmentPreBookArrival"></strong></h3>
            <h3>Departure: <strong id="apartmentPreBookDeparture"></strong></h3>
            &nbsp;
            <form id="apartmentPreBookForm">
                <div class="form-floating">
                    <input type="text" name="apartmentGuestName" class="form-control"
                           id="guestNamePreBookApartment"
                           placeholder="Mock Guest"
                           required>
                    <label for="guestNamePreBookApartment">Guest Name</label>
                </div>
                &nbsp;
                <div class="text-center">
                    <button id="apartmentPreBookConfirmButton" class="btn btn-warning" onclick="bookApartment()">Confirm
                        booking
                    </button>
                    &nbsp;
                    <button class="btn btn-danger" onclick="resetNewBooking()">Search again</button>
                </div>
            </form>
        </div>
        <div id="hotelPreBookDiv">

            <h3>Type: <strong>Apartment</strong></h3>
            <h3>Name: <strong id="hotelPreBookName"></strong></h3>
            <h3>Total price: <strong id="hotelPreBookPrice"></strong></h3>
            <h3>Total half board price: <strong id="hotelPreBookHalfBoardPrice"></strong></h3>
            <h3>Arrival: <strong id="hotelPreBookArrival"></strong></h3>
            <h3>Departure: <strong id="hotelPreBookDeparture"></strong></h3>
            &nbsp;
            <form id="hotelPreBookForm">
                <div class="form-floating">
                    <input type="text" name="hotelGuestName" class="form-control"
                           id="guestNamePreBookHotel"
                           placeholder="Mock Guest"
                           required>
                    <label for="guestNamePreBookHotel">Guest Name</label>
                </div>
                &nbsp; &nbsp;
                <div class="form-check text-left">
                    <input class="form-check-input" name="halfBoard" type="checkbox" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        Require Half Board
                    </label>
                </div>
                &nbsp;
                <div class="text-center">
                    <button id="hotelPreBookConfirmButton" class="btn btn-warning" onclick="bookHotel()">Confirm
                        booking
                    </button>
                    &nbsp;
                    <button class="btn btn-danger" onclick="resetNewBooking()">Search again</button>
                </div>
            </form>
        </div>
        <div id="noAccommodationsDiv" class="text-center">
            <h2>No valid accommodations available!</h2>
            &nbsp;
            <button class="btn btn-danger" onclick="resetNewBooking()">Search again</button>
        </div>
        <div id="newBookSuccessDiv" class="text-center">
            <h2>Book successful!</h2>
            &nbsp;
            <button class="btn btn-warning" onclick="resetNewBooking()">OK</button>
        </div>
    </div>
</div>
&nbsp;
<!-- ------------------------------------------------------------------------------------------------------------- -->
<hr>
<h3 class=" d-flex justify-content-center">Search for a booking</h3>
&nbsp;
<div class="container d-flex justify-content-center">
    <div class=" w-50"
         style="border-style:solid; border-width: 1px; border-radius: 30px; padding: 20px;">
        <div id="searchBookingsDiv">
            <form id="searchBookingsForm">
                <div class="form-floating">
                    <input type="" class="form-control" id="guestNameInputValueSearch" name="searchGuestName"
                           placeholder="1" required>
                    <label for="guestNameInputValueSearch">Name</label>
                </div>
                &nbsp;
                <div class="text-center">
                    <button class="btn btn-warning" onclick="searchBookings()">Search bookings</button>
                </div>
            </form>
        </div>
        <div id="searchBookingsLoading" class="text-center">
            <div class="spinner-border text-warning" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
        <div id="noBookingsDiv" class="text-center">
            <h2>No bookings available!</h2>
            &nbsp;
            <button class="btn btn-danger" onclick="resetSearchBookings()">Search again</button>
        </div>
        <div id="searchBookingsResultDiv">
            <div style="overflow-y: auto; max-height:330px">
                <ol id="bookingsList" class="list-group list-group">
                </ol>
            </div>
            &nbsp;
            <div class="text-center">
                <button class="btn btn-danger" onclick="resetSearchBookings()">Search again</button>
            </div>
        </div>
    </div>
</div>
&nbsp;
<script>

    var days = null;
    var arrival = null;
    var departure = null;
    var persons = null;

    function askForAvailableAccommodations() {

        if (new Date($("#arrivalInputValue").val()) >= new Date($("#departureInputValue").val())) {
            alert("Choose a valid departure date!");
        } else {

            $('#newBookingForm').on('submit', function (e) {
                e.preventDefault();

                $('#newBookingForm').hide();
                $('#newBookingLoading').show();

                $.ajax({
                    type: 'GET',
                    url: 'getAvailableAccommodations',
                    data: $('form').serialize(),
                    success: function (result) {

                        if (result.apartments.length == 0 && result.hotels.length == 0) {
                            $('#newBookingLoading').hide();
                            $('#noAccommodationsDiv').show();
                        } else {

                            for (var a = 0; a < result.apartments.length; a++) {
                                $('#availableAccommodationsListGroup').append("<input class=\"list-group-item-check\" type=\"radio\" " +
                                    "name=\"listAvailableAccommodations\" id=\"listAvailableAccommodations" + a + "\" " +
                                    "accName=\"" + result.apartments[a].name + "\" accType=\"" + 0 + "\" accId=\"" + result.apartments[a].id +
                                    "\" accPrice=\"" + result.apartments[a].price +
                                    "\" cleaningPrice=\"" + result.apartments[a].cleaningPrice
                                    + "\"> <label class=\"list-group-item py-3\" " +
                                    "for=\"listAvailableAccommodations" + a + "\"> <strong>" + result.apartments[a].name +
                                    "</strong> <span class=\"d-block small\">" +
                                    "Type: <strong>Apartment</strong></span> <span class=\"d-block small\">" +
                                    "Price: <strong>" + result.apartments[a].price + "</strong></span> <span class=\"d-block small\">" +
                                    "Cleaning price: <strong>" + result.apartments[a].cleaningPrice + "</strong></span></label></div>");
                            }


                            for (var h = 0; h < result.hotels.length; h++) {
                                $('#availableAccommodationsListGroup').append("<input class=\"list-group-item-check\" type=\"radio\" " +
                                    "name=\"listAvailableAccommodations\" id=\"listAvailableAccommodations" + (a + h + 1) + "\" " +
                                    "accName=\"" + result.hotels[h].name + "\" accType=\"" + 1 + "\" accId=\"" + result.hotels[h].id +
                                    "\" accPrice=\"" + result.hotels[h].price +
                                    "\" halfBoardPrice=\"" + result.hotels[h].halfBoardPrice
                                    + "\"> <label class=\"list-group-item py-3\" " +
                                    "for=\"listAvailableAccommodations" + (a + h + 1) + "\"> <strong>" + result.hotels[h].name +
                                    "</strong> <span class=\"d-block small\">" +
                                    "Type: <strong>Hotel</strong></span> <span class=\"d-block small\">" +
                                    "Price: <strong>" + result.hotels[h].price + "</strong></span> <span class=\"d-block small\">" +
                                    "Half Board price: <strong>" + result.hotels[h].halfBoardPrice + "</strong></span></label></div>");
                            }

                            days = result.days;
                            arrival = result.arrival;
                            departure = result.departure;
                            persons = result.persons;

                            $('#newBookingLoading').hide();
                            $('#availableAccommodationsListDiv').show();
                        }
                    },
                    error: function () {
                        $('#newBookingLoading').hide();
                        $('#newBookingForm').show();
                        alert("Error!");
                        console.log("Error");
                    }
                });
            });
        }
    }

    function bookApartment() {

        $('#apartmentPreBookForm').on('submit', function (a) {

            a.preventDefault();

            $('#apartmentPreBookDiv').hide();
            $('#newBookingLoading').show();

            $.ajax({
                type: 'POST',
                url: 'bookApartment',
                data: $('form').serialize(),
                success: function () {

                    $('#newBookingLoading').hide();
                    $('#newBookSuccessDiv').show();
                },
                error: function () {
                    alert("Error in booking!");
                    resetNewBooking();
                }
            });
        })
    }

    function bookHotel() {

        $('#hotelPreBookForm').on('submit', function (a) {
            a.preventDefault();
            $('#hotelPreBookDiv').hide();
            $('#newBookingLoading').show();

            $.ajax({
                type: 'POST',
                url: 'bookHotel',
                data: $('form').serialize(),
                success: function () {
                    console.log("Hotel booked");
                    $('#newBookingLoading').hide();
                    $('#newBookSuccessDiv').show();
                },
                error: function () {
                    alert("Error in booking!");
                    resetNewBooking();
                }
            });
        })
    }

    function preBook() {
        var selectedId;
        var price;
        var type;
        var name

        $('#availableAccommodationsListGroup input:checked').each(function () {
            selectedId = ($(this).attr('accId'));
            type = ($(this).attr('accType'));
            price = ($(this).attr('accPrice'));
            name = ($(this).attr('accName'));
        });

        if (type == 0) {

            var cleaningPrice;

            $('#availableAccommodationsListGroup input:checked').each(function () {
                cleaningPrice = ($(this).attr('cleaningPrice'));

                $('#apartmentPreBookName').html(name);
                $('#apartmentPreBookPrice').html(price * days);
                $('#apartmentPreBookCleaningPrice').html(cleaningPrice * days);
                $('#apartmentPreBookArrival').html(arrival);
                $('#apartmentPreBookDeparture ').html(departure);

                $('#apartmentPreBookForm').append("<input type=\"hidden\" name=\"id\" value=\"" + selectedId + "\" required>");
                $('#apartmentPreBookForm').append("<input type=\"hidden\" name=\"arrival\" value=\"" + arrival + "\" required>");
                $('#apartmentPreBookForm').append("<input type=\"hidden\" name=\"departure\" value=\"" + departure + "\" required>");

                $('#availableAccommodationsListDiv').hide();
                $('#apartmentPreBookDiv').show();
            });
        } else if (type == 1) {

            var halfBoardPrice;

            $('#availableAccommodationsListGroup input:checked').each(function () {
                halfBoardPrice = ($(this).attr('halfBoardPrice'));

                $('#hotelPreBookName').html(name);
                $('#hotelPreBookPrice').html(price * days);
                $('#hotelPreBookHalfBoardPrice').html(halfBoardPrice * days);
                $('#hotelPreBookArrival').html(arrival);
                $('#hotelPreBookDeparture ').html(departure);

                $('#hotelPreBookForm').append("<input type=\"hidden\" name=\"id\" value=\"" + selectedId + "\" required>");
                $('#hotelPreBookForm').append("<input type=\"hidden\" name=\"arrival\" value=\"" + arrival + "\" required>");
                $('#hotelPreBookForm').append("<input type=\"hidden\" name=\"departure\" value=\"" + departure + "\" required>");

                $('#availableAccommodationsListDiv').hide();
                $('#hotelPreBookDiv').show();
            });
        }
    }

    function searchBookings() {

        $('#searchBookingsForm').on('submit', function (e) {
            e.preventDefault();

            $('#searchBookingsDiv').hide();
            $('#searchBookingsLoading').show();

            $.ajax({
                type: 'GET',
                url: 'searchBookings',
                data: $('form').serialize(),
                success: function (result) {

                    if (result.bookings.length == 0) {

                        $("#searchBookingsLoading").hide();
                        $("#noBookingsDiv").show();
                    } else {
                        for (var i = 0; i < result.bookings.length; i++) {

                            $("#bookingsList").append("<li class=\"list-group-item d-flex justify-content-between align-items-start\" " +
                                "style=\"border-radius: 30px;\"> <div class=\"ms-2 me-auto\"> <div class=\"fw-bold\">" + result.bookings[i].accName + "</div>" + "" +
                                "<div class=\"container\"> <span class=\"row\">Arrival: <strong>" + result.bookings[i].arrival + "</strong> </span>" +
                                "<span class=\"row\">Departure: <strong>" + result.bookings[i].departure + "</strong> </span></div></div>" +
                                "<span id=\"bookingType\" class=\"badge bg-primary rounded-pill\">" + result.bookings[i].type + "</span></li> &nbsp;");
                        }

                        $("#searchBookingsLoading").hide()
                        $("#searchBookingsResultDiv").show()

                    }
                },
                error: function () {

                    alert("Error in retrieving bookings!");
                    resetSearchBookings();

                }
            });
        });

    }

    $(document).ready(function () {
        $('#newBookSuccessDiv').hide();
        $('#availableAccommodationsListDiv').hide();
        $('#noAccommodationsDiv').hide();
        $('#newBookingLoading').hide();
        $('#apartmentPreBookDiv').hide();
        $('#hotelPreBookDiv').hide();
        $('#searchBookingsLoading').hide();
        $('#searchBookingsResultDiv').hide();
        $('#noBookingsDiv').hide();
    });

    function resetNewBooking() {
        days = null;
        arrival = null;
        departure = null;
        persons = null;

        $('#newBookSuccessDiv').hide();
        $('#availableAccommodationsListDiv').hide();
        $('#noAccommodationsDiv').hide();
        $('#newBookingLoading').hide();
        $('#apartmentPreBookDiv').hide();
        $('#hotelPreBookDiv').hide();
        $('#availableAccommodationsListGroup').html("");
        $("#newBookingForm").trigger("reset");
        $("#apartmentPreBookForm").trigger("reset");
        $("#hotelPreBookForm").trigger("reset");
        $("#newBookingForm").show();
    }

    function resetSearchBookings() {
        $('#searchBookingsLoading').hide();
        $('#searchBookingsForm').trigger("reset");
        $('#searchBookingsResultDiv').hide();
        $('#noBookingsDiv').hide();
        $('#bookingsList').html("");
        $('#searchBookingsDiv').show();
    }


</script>
</body>
</html>
