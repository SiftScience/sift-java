package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {
    @Expose @SerializedName("$departure_address") private Address departureAddress;
    @Expose @SerializedName("$arrival_address") private Address arrivalAddress;
    @Expose @SerializedName("$start_time") private Long startTime;
    @Expose @SerializedName("$end_time") private Long endTime;
    @Expose @SerializedName("$vessel_number") private String vesselNumber;
    @Expose @SerializedName("$arrival_airport_code") private String arrivalAirportCode;
    @Expose @SerializedName("$departure_airport_code") private String departureAirportCode;
    @Expose @SerializedName("$fare_class") private String fareClass;
    @Expose @SerializedName("$iata_carrier_code") private String iataCarrierCode;


    public Address getDepartureAddress() {
        return departureAddress;
    }

    public Segment setDepartureAddress(Address departureAddress) {
        this.departureAddress = departureAddress;
        return this;
    }

    public Address getArrivalAddress() {
        return arrivalAddress;
    }

    public Segment setArrivalAddress(Address arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Segment setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Segment setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public Segment setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
        return this;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public Segment setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
        return this;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public Segment setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    public String getFareClass() {
        return fareClass;
    }

    public Segment setFareClass(String fareClass) {
        this.fareClass = fareClass;
        return this;
    }

    public String getIataCarrierCode() {
        return iataCarrierCode;
    }

    /**
     * Sets the IATA carrier code for the flight segment.
     *
     * <p>This field should only be set when the booking type is {@code $flight}.</p>
     *
     * @param iataCarrierCode The IATA carrier code.
     * @return The updated {@code Segment} object.
     */
    public Segment setIataCarrierCode(String iataCarrierCode) {
        this.iataCarrierCode = iataCarrierCode;
        return this;
    }
}
