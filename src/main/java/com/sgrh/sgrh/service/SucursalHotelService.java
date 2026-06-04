package com.sgrh.sgrh.service;

import java.util.List;

import com.sgrh.sgrh.dto.SucursalHotelDTO;

public interface SucursalHotelService {
    SucursalHotelDTO crearSucursalHotel(SucursalHotelDTO sucursalHotelDTO);

    SucursalHotelDTO obtenerSucursalHotelById(Integer idSucursal);

    SucursalHotelDTO obtenerSucursalHotelByNombre(String nombre);

    List<SucursalHotelDTO> obtenerTodas();

    SucursalHotelDTO actualizarSucursalHotel(Integer idSucursal, SucursalHotelDTO sucursalHotelDTO);

    void eliminarSucursalHotel(Integer idSucursal);
}
