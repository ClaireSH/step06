package step6.manager;
import java.io.IOException;
import java.util.ArrayList;

import step06.Exeption.DuplicateJuminException;
import step06.Exeption.RecordNotFoundException;
import step06.vo.Human;

public interface SESchoolManagerImpl {
	/**
	 *  구현 implements, 다중 구현 가능 다중 상속 처럼
	 * 
	 * @param h
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws DuplicateJuminException 
	 */

	//입력
	public boolean insertHuman(Human h) throws IOException, ClassNotFoundException, DuplicateJuminException;
	//검색
	public Human searchHuman(String jumin) throws IOException;
	
	
	//수정
	public boolean updateHuman(Human uh) throws RecordNotFoundException;
	
	//삭제
	public boolean deleteHuman(String jumin) throws IOException, ClassNotFoundException, RecordNotFoundException;

}
