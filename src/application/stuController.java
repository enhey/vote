package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class stuController implements Initializable {
	 @FXML
	    private TableColumn<List, String> give;

	    @FXML
	    private TableColumn<List, String> name;
	    
	    @FXML
	    private TableColumn<List,Integer> tickets;

	    @FXML
	    private Text hello;

	    @FXML
	    private Text title;

	    @FXML
	    private Text message;

	    @FXML
	    private TableView<List> vote;
	    
	    @FXML
	    private ComboBox<List> times;
	    
	    @FXML
	    private Button signout;
	    String SelfId;
	private innit myapp;
	private Basic base=new Basic();
	public void setapp(innit myapp) {
		this.myapp=myapp;
		this.base=myapp.getbase();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setTimes();
		setVoteTableview(1);
	}
	public void setTimes() {
		ObservableList<List> collectlist=FXCollections.observableArrayList();
		collectlist= base.settimes();
		times.setItems(collectlist);
		times.getSelectionModel().select(0);
	}
	
	
	
	public void setCondidateList(int num) {
		ObservableList<List>collectlist=FXCollections.observableArrayList();
		collectlist=base.getCondidateList(num);	
		name.setCellValueFactory(new PropertyValueFactory<List, String>("sdata"));
		vote.setItems(collectlist);
	}
	
	public void setVoteBt(int num)
	{
		 give.setCellFactory((col) -> {
	            TableCell<List, String> cell = new TableCell<List, String>() {
	            	
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    this.setText(null);
	                    this.setGraphic(null);

	                    if (!empty) {
//	                        ImageView voteICON = new ImageView(getClass().getResource("delete.png").toString());
	                        Button voteBtn = new Button("投票");
	                        this.setGraphic(voteBtn);
	                        String condidateName = this.getTableView().getItems().get(this.getIndex()).getSdata();
	                        if(base.WeatheVoted(num, condidateName)) {	                  
	                        	voteBtn.setText("已投");
	                        	voteBtn.setDisable(true);;
	                        }
	                        //添加button监听
	                        voteBtn.setOnMouseClicked((me) -> {
	               //         	String condidateName = this.getTableView().getItems().get(this.getIndex()).getSdata();
	                        	base.GiveVote(num, condidateName);
	              			  JOptionPane.showMessageDialog(null, "投票成功");
	              			  setVoteTableview(num);	                        
	                        });
	                    }
	                }

	            };
	            return cell;
	        });
	}
	
	public void setVoteNum(int num) {
		
		 tickets.setCellFactory((col) -> {
	            TableCell<List, Integer> cell = new TableCell<List, Integer>() {
	            	
	                @Override
	                public void updateItem(Integer item, boolean empty) {
	                    super.updateItem(item, empty);
	                    this.setText(null);
	                    this.setGraphic(null);

	                    if (!empty) {
	                    	String condidateName = this.getTableView().getItems().get(this.getIndex()).getSdata();
	                    	this.setText(String.valueOf(base.getCondidateListTiket(num,condidateName )));

	                    }
	                }

	            };
	            return cell;
	        });
	}
	
	public void setVoteTableview(int num) {
		setCondidateList(num);
		setVoteBt(num);
		setVoteNum(num);
	}
	
    @FXML
    public void timesAction(ActionEvent event) {
    	String text = null;
		int num = 0;
		text=times.getValue().toString();
		num=Integer.parseInt(text.substring(1, 2));
		 setVoteTableview(num);

    }

    @FXML
    void signout(ActionEvent event) {
    	myapp.homeui();
		base.closedata();

    }
}
