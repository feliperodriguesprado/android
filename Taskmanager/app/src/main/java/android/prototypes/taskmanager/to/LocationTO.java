package android.prototypes.taskmanager.to;

import com.google.gson.annotations.SerializedName;

public class LocationTO {

    @SerializedName(value = "cep")
    private String zipCode;
    @SerializedName(value = "logradouro")
    private String street;
    @SerializedName(value = "complemento")
    private String complement;
    @SerializedName(value = "bairro")
    private String district;
    @SerializedName(value = "localidade")
    private String locality;
    private String uf;
    //private String ibge;
    //private String gia;

    public LocationTO() {
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    /*public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }*/
}
