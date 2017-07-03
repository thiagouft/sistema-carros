package br.com.thiago.carros.bean;

import br.com.thiago.carros.dao.CarroDAO;
import br.com.thiago.carros.entidade.Carro;
import br.com.thiago.carros.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Thiago
 */

@ManagedBean
@SessionScoped
public class CarroBean {
    
    private Carro carro = new Carro();
    private List<Carro> carros = new ArrayList<>();
    private CarroDAO carroDAO = new CarroDAO();
    
    public void adicionar(){
        try {
            //carros.add(carro);
            new CarroDAO().salvar(carro);
            carro = new Carro();
            adicionarMensagem("Salvo!","Carro Salvo com Sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void listar(){
        try {
            carros = carroDAO.buscar();
            if(carros == null || carros.size() == 0){
                adicionarMensagem("Nenhum Carro Encontrado!!", "Sua Busca Não Retornou Nenhum Carro", FacesMessage.SEVERITY_WARN);
                
            }
        } catch (ErroSistema ex) {
           adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Carro c){
        carro = c;
    }
    
    public void deletar(Carro c){
        try {
            carroDAO.deletar(c.getId());
            adicionarMensagem("Deletado!","Carro Deletado com Sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);;
        }
    }
    
    public void adicionarMensagem(String sumario, String detalhe,FacesMessage.Severity tipoErro){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
        
                
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
    
    
    
    
    
}
