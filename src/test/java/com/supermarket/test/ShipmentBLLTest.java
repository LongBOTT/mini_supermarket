package com.supermarket.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.supermarket.BLL.ShipmentBLL;
import com.supermarket.DTO.Shipment;
import com.supermarket.utils.Date;

public class ShipmentBLLTest{
    ShipmentBLL shipmentBLL = new ShipmentBLL();

    Shipment shipment = new Shipment(1, 1, 23.0, 20.0, 20.0, new Date(22, 10, 2013),new Date(22, 10, 2014),"123",1,false); 

    @Test
    public void arrayTest(){
        for(Shipment shipmentRow : shipmentBLL.getShipmentList()){
            System.out.println(shipmentRow.getQuantity());
        }
    }

    @Test
    public void addTest() {
        boolean success = shipmentBLL.addShipment(shipment);
        assertTrue(success);
    }

    @Test
    public void updateTest() {
        shipment.setQuantity(40);
        boolean success = shipmentBLL.updateShipment(shipment);
        assertTrue(success);
    }

    @Test
    public void removeTest() {
        boolean success = shipmentBLL.deleteShipment(shipment);
        assertTrue(success);
    }
}