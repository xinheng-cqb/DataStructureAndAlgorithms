/**
 * @author xinheng-cqb
 * @date 2018年2月9日
 * @introduce: 平衡二叉查找树相关知识 
 * 平衡二叉树查找树的难点：
 * 1、要理解左旋、右旋是怎么操作的
 * 2、理解单旋和双旋，
 * 3、插入时要进行旋转条件以及旋转的方向和次数
 * 4、删除时要进行旋转条件以及旋转的方向和次数
 * 5、节点变动（插入、删除）时要更新整个树上相关的节点高度
 * 以上内容都搞定了，类基本上就写好了，然后就是各种调试把遗漏的条件或出错的地方改正。
 * ps:我的代码里计算树高是在Node类里进行的，也可以在Tree类写个函数进行计算。插入和删除都测试了好多次，但是还是不保证会有bug，如果发现了，请指出，谢谢！
 */
package structure.AVL;