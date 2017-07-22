package shui.utiltools.generator.impl;

import shui.utiltools.generator.LengthPrimaryKeyGenerator;
import shui.utiltools.generator.exception.IllegalParamException;
import shui.utiltools.generator.exception.LengthTooLongException;

/**
 * Long型数字主键生成器，随机生成long型数字主键
 * @author shuibaoqin
 *
 */
public class LongPrimaryKeyGenerator implements LengthPrimaryKeyGenerator<Long>{
	@Override
	public Long generatePrimaryKey() {
		return this.generatePrimaryKey(19);
	}
	@Override
	public Long generatePrimaryKey(int length) {
		if(length>0){
			if(length<=19){
				long result=0l;
				if(length==19){
					result=result+(int)(Math.random()*9);
					length--;
				}
				while((length--)>0){
					result=result*10+(int)(Math.random()*10);
				}
				return result;
			}else{
				throw new LengthTooLongException("输入的长度过长，Long型数据最大：9223372036854775807");
			}
		}else{
			throw new IllegalParamException("长度必须是正数!");
		}
	}

}
