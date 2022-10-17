package org.gin.emuns;

/**
 * 表情贴图
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 14:13
 */
public enum Stamp {
    /**
     * 表情贴图
     * 图片地址为： https://s.pximg.net/common/images/stamp/generated-stamps/${id}_s.jpg
     */

    Stamp_101(101),
    Stamp_102(102),
    Stamp_103(103),
    Stamp_104(104),
    Stamp_105(105),
    Stamp_106(106),
    Stamp_107(107),
    Stamp_108(108),
    Stamp_109(109),
    Stamp_110(110),
    Stamp_201(201),
    Stamp_202(202),
    Stamp_203(203),
    Stamp_204(204),
    Stamp_205(205),
    Stamp_206(206),
    Stamp_207(207),
    Stamp_208(208),
    Stamp_209(209),
    Stamp_210(210),
    Stamp_301(301),
    Stamp_302(302),
    Stamp_303(303),
    Stamp_304(304),
    Stamp_305(305),
    Stamp_306(306),
    Stamp_307(307),
    Stamp_308(308),
    Stamp_309(309),
    Stamp_310(310),
    Stamp_401(401),
    Stamp_402(402),
    Stamp_403(403),
    Stamp_404(404),
    Stamp_405(405),
    Stamp_406(406),
    Stamp_407(407),
    Stamp_408(408),
    Stamp_409(409),
    Stamp_410(410),

    ;
    final int id;

    Stamp(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
