package shui.utiltools.generator.impl;

import shui.utiltools.generator.LengthPrimaryKeyGenerator;
import shui.utiltools.generator.exception.IllegalParamException;
import shui.utiltools.generator.exception.LengthTooLongException;

/**
 * 数字主键生成器，随机生成Integer数字主键
 * @author shuibaoqin
 *
 */
public class IntegerPrimaryKeyGenerator implements LengthPrimaryKeyGenerator<Integer> {

	@Override
	public Integer generatePrimaryKey() {
		return this.generatePrimaryKey(10);
	}

	@Override
	public Integer generatePrimaryKey(int length) {
		if (length>0) {
			int result=0,num;
			if(length <= 10){
				if(length==10){
					result=result*10+(int)(Math.random()*2);
					length--;
				}
				while((length--)>0){
					result=result*10+(int)(Math.random()*10);
				}
				return result;
			} else {
				throw new LengthTooLongException("Integer类型最大只到10位，最大：2147483647");
			}
		}else{
			throw new IllegalParamException("输入的长度必须是正数!");
		}
	}

}
